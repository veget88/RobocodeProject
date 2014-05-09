package org.pattern.radar;

import org.robot.Enemy;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class GBulletFiredEvent {

	private Enemy firingRobot;
	private double velocity;
	private long firingTime;
	private double energy;
	
	double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public long getFiringTime() {
		return firingTime;
	}
	
	public void setFiringTime(long firingTime) {
		this.firingTime = firingTime;
	}
	
	public Enemy getFiringRobot() {
		return firingRobot;
	}
	
	public void setFiringRobot(Enemy firingRobot) {
		this.firingRobot = firingRobot;
	}
	
	public double getVelocity() {
		return velocity;
	}
	
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	
	public GBulletFiredEvent() {
		
	}
	
	public GBulletFiredEvent(ScannedRobotEvent event, AdvancedRobot robot) {
		firingRobot = new Enemy(event, robot);

	}

	
}
