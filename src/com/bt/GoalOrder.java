package com.bt;

public class GoalOrder {
	int Order;
	int Goal;
	
	GoalOrder(int order,int  goal) {
		Order = order;
		Goal = goal;
	}

	int getOrder() {
		return Order;
	}

	int getGoal() {
		return Goal;
	}
}
