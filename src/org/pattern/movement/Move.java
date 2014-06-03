package org.pattern.movement;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import robocode.AdvancedRobot;

public class Move {
	public int ahead;
	public double turnRight;
	
	private Rectangle2D safeBF;
	private int STICK_LENGTH = 140;
	private int MINIMUM_RADIUS = 114;
	
	public Move(AdvancedRobot robot) {
		safeBF = new Rectangle2D.Double(18, 18,
				robot.getBattleFieldWidth() - 36, robot.getBattleFieldHeight() - 36);
	}
	
	public void move(double angle, double startHeading) {
		ahead = 1;
		if (Math.abs(robocode.util.Utils.normalRelativeAngleDegrees(angle
				- startHeading)) > 90) {
			ahead = -1;
			angle += 180;
		}

		turnRight = robocode.util.Utils.normalRelativeAngleDegrees(angle
				- startHeading);
	}
	
	public boolean smooth(Point2D position, double heading,
			double wantedHeading, int direction) {
	
		if (!stickCollide(position, ahead == 1 ? heading : heading + 180))
			return false;

		Point2D center1 = new Point2D.Double(position.getX()
				+ Math.sin(Math.toRadians(heading - 90)) * MINIMUM_RADIUS,
				position.getY() + Math.cos(Math.toRadians(heading - 90))
						* MINIMUM_RADIUS);
		Point2D center2 = new Point2D.Double(position.getX()
				+ Math.sin(Math.toRadians(heading + 90)) * MINIMUM_RADIUS,
				position.getY() + Math.cos(Math.toRadians(heading + 90))
						* MINIMUM_RADIUS);
		Boolean smoothC1 = canSmooth(center1);
		Boolean smoothC2 = canSmooth(center2);
		if (!smoothC1 && !smoothC2) {
			ahead = direction * -1;
			turnRight = 0;

		} else if (smoothC1 && direction == 1 || smoothC2 && direction == -1) {
			if (wantedHeading - heading > -4) {
				ahead = direction;
				turnRight = -4;
			}

		} else if (smoothC2 && direction == 1 || smoothC1 && direction == -1) {
			if (wantedHeading - heading < 4) {
				ahead = direction;
				turnRight = 4;
			}
		}
		return true;
	}
	
	private boolean stickCollide(Point2D p, Double heading) {
		Double endX = p.getX() + Math.sin(Math.toRadians(heading))
				* STICK_LENGTH;
		Double endY = p.getY() + Math.cos(Math.toRadians(heading))
				* STICK_LENGTH;

		if (!safeBF.contains(endX, endY)) {
			return true;
		}

		return false;
	}

	private Boolean canSmooth(Point2D center) {
		Point2D up, down, left, right;
		up = new Point2D.Double(center.getX(), center.getY() + MINIMUM_RADIUS);
		down = new Point2D.Double(center.getX(), center.getY() - MINIMUM_RADIUS);
		left = new Point2D.Double(center.getX() - MINIMUM_RADIUS, center.getY());
		right = new Point2D.Double(center.getX() + MINIMUM_RADIUS,
				center.getY());
		
		if (!safeBF.contains(up) || !safeBF.contains(right)
				|| !safeBF.contains(down) || !safeBF.contains(left)) {
			return false;
		}
		return true;
	}
	
}
