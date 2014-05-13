package org.robot;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import org.pattern.movement.MAE;
import org.pattern.movement.Projection.tickProjection;
import org.pattern.utils.Utils;

import robocode.AdvancedRobot;

public class MAETester extends AdvancedRobot {

	public Point2D bulletPosition = new Point2D.Double(100, 200);
	public double bulletEnergy = 2.0;
	private MAE preciseMAE;
	
	public MAETester() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		preciseMAE = new MAE(bulletPosition, new Point2D.Double(getX(), getY()), getHeading(), getVelocity(), (20 - 3 * bulletEnergy));
		
		double bestHeading = robocode.util.Utils.normalAbsoluteAngleDegrees(Utils.absBearing(new Point2D.Double(getX(), getY()), bulletPosition) - 90);
		
		setTurnRight(bestHeading - getHeading());
		setAhead(500);
		execute();
	
	}
	
	@Override
	public void onPaint(Graphics2D g) {
		//paint preciseMAE
		
		for (tickProjection pTick : preciseMAE.getProjections()) {
			g.drawRect((int)pTick.getPosition().getX()-2, (int)pTick.getPosition().getY()-2, 4, 4);
		}
		
		long tick = getTime();
		double radius = (20 - 3 * bulletEnergy) * (tick);
   			
			/* the bullet is fired from cannon that is displaced 10px from the center of the robot */
		g.drawArc((int)(bulletPosition.getX() - radius), (int)(bulletPosition.getY() - radius), (int)radius*2, (int)radius*2, 0, 360);
		
		g.drawLine((int)getX(), (int)getY(), (int)bulletPosition.getX(), (int)bulletPosition.getY());
		
	}
	
}