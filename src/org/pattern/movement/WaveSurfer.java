package org.pattern.movement;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

import org.pattern.radar.GBulletFiredEvent;
import org.pattern.utils.Costants;
import org.pattern.utils.Utils;

import robocode.AdvancedRobot;
import robocode.BulletHitBulletEvent;
import robocode.HitByBulletEvent;

public class WaveSurfer {
	private AdvancedRobot robot;
	private List<GBulletFiredEvent> bullets;

	
	public WaveSurfer(AdvancedRobot robot) {
		this.robot = robot;
		bullets = new LinkedList<>();
	}
	public void addWave(GBulletFiredEvent bullet) {
		bullets.add(bullet);
	}
	
	public void removePassedWaves() {
		//TODO do with iterator
		LinkedList<GBulletFiredEvent> bullet_copy = new LinkedList<>();
		bullet_copy.addAll(bullets);
		for (GBulletFiredEvent bullet : bullet_copy) {
			if ((robot.getTime() - bullet.getFiringTime()) * bullet.getVelocity() > new Point2D.Double(robot.getX(), robot.getY()).distance(bullet.getFiringPosition()) + Costants.SURFING_REMOVE_WAVE_OFFSET) {
//				robot.out.println("Removing waves, current gf is " + Utils.calculateGF(bullet, new Point2D.Double(robot.getX(), robot.getY())));
				bullets.remove(bullet);
			}
		}
	}
	
	public GBulletFiredEvent getNearestWave() {
		double min = Double.MAX_VALUE;
		GBulletFiredEvent ret = null;
		Point2D myPos = new Point2D.Double(robot.getX(), robot.getY());
		for (GBulletFiredEvent bullet : bullets) {
			double distance = bullet.getFiringPosition().distance(myPos) - (robot.getTime() - bullet.getFiringTime()) * bullet.getVelocity();
			if (distance < min ) {
				min = distance;
				ret = bullet;
			}
		}
		return ret;
	}
	
	public List<GBulletFiredEvent> getWaves() {
		return bullets;
	}
	

}
