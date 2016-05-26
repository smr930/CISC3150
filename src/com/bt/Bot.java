package com.bt;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

public class Bot {
		protected Point topLeft;
		protected int currPosition;
		protected int size;
		protected Color color;
		protected int order;
		protected int goal;
		boolean nextRow;
		LinkedList<GoalOrder> Directions;

		Bot(int x, int y, int size, Color color) {
			topLeft = new Point(x, y);
			this.size = size;
			this.color = color;
			this.currPosition = 1;
			Directions = new LinkedList<GoalOrder>();
			nextRow = false;
		}

		void move() {
			if(goal > currPosition) {
				if(currPosition < 50) {
					topLeft.y = 100;
					topLeft.x += 20;
				}
				
				if(currPosition == 50) {
					nextRow = true;
					topLeft.x = 0;
				}
				if(nextRow == true) {
				topLeft.y = 140;
				topLeft.x += 20;
				}
				currPosition++;
			}else if (goal < currPosition) {
				topLeft.x -= 20;
				currPosition--;
			}
		}

		void restart() {
			topLeft.x = 0;
			currPosition = 1;
		}

		void assign() {
			if(Directions.isEmpty()) {
				return;
			}

			GoalOrder Q = Directions.removeFirst();		
			this.goal = Q.getGoal();
			this.order = Q.getOrder();
			return;
		}

		void addPair(int Order, int Goal) {
			GoalOrder currPair = new GoalOrder(Order, Goal);
			Directions.add(currPair);
			return;
		}
		
		int getOrder() {
			return order;
		}

		int getPosition() {
			return currPosition;
		}

		int getGoal() {
			return goal;
		}
}
