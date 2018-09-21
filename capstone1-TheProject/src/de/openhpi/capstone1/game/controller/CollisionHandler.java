package de.openhpi.capstone1.game.controller;

import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {

	private List<CollisionChecker> collisionCheckers = new ArrayList<>();
	private List<Class<?>>collisionClasses = new ArrayList<>();
	
	public void registerCollisionChecker(Class<?>clazz,CollisionChecker checker) {
		collisionCheckers.add(checker);
		collisionClasses.add(clazz);
	}

	public CollisionResult checkCollision(int x, int y) {

		CollisionResult result = null;
		for(int cntChecker=0;cntChecker<collisionCheckers.size();cntChecker++) {
			Object resultArg = collisionCheckers.get(cntChecker).hasCollision(x, y);
			if(resultArg!=null) {
				result = new CollisionResult(collisionClasses.get(cntChecker),resultArg);
				break;
			}
		}
		
		return result!=null?result:new CollisionResult();
	}

	@FunctionalInterface
	public static interface CollisionChecker {
		Object hasCollision(int x, int y);
	}

	@FunctionalInterface
	public static interface CollisionConsumer {
		void identifyCollision(boolean hasCollision);
	}
	
	public static class CollisionResult{
		private Class<?> collisionType;
		private Object argument;
		
		private CollisionResult() {}
		
		private CollisionResult(Class<?> collisionType,Object argument) {
			this.collisionType = collisionType;
			this.argument = argument;
		}
		
		public Class<?> getCollisionType() {
			return collisionType;
		}
		
		public Object getArgument(){
			return argument;
		}
		
		public boolean isEmpty() {
			return collisionType==null;
		}
	}
}