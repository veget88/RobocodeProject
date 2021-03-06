package org.pattern.radar;

import java.awt.geom.Point2D;
import java.util.BitSet;

import org.pattern.movement.MAE;
import org.robot.Enemy;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class GBulletFiredEvent {

	private Enemy firingRobot;
	private double velocity;
	private long firingTime;
	private double energy;
	private Point2D firingPosition;
	private Point2D targetPosition;
	private double minMAE, maxMAE;
	private BitSet snapshot;
	
	public BitSet getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(BitSet snapshot) {
		this.snapshot = snapshot;
	}

	//valid only for bullet that _we_ fire
	public double firingGF;
	
	public double getMinMAE() {
		return minMAE;
	}

	public void setMinMAE(double minMAE) {
		this.minMAE = minMAE;
	}

	public double getMaxMAE() {
		return maxMAE;
	}

	public void setMaxMAE(double maxMAE) {
		this.maxMAE = maxMAE;
	}

	public Point2D getTargetPosition() {
		return targetPosition;
	}

	public void setTargetPosition(Point2D targetPosition) {
		this.targetPosition = targetPosition;
	}


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
		targetPosition = new Point2D.Double(robot.getX(), robot.getY());
	}

	public Point2D getFiringPosition() {
		return firingPosition;
	}

	public void setFiringPosition(Point2D firingPosition) {
		this.firingPosition = firingPosition;
	}

	
}
