package org.act.od.impl.model
{
	/**
	 * The manager of FigureID.
	 */
	public class FigureIDManager
	{
		private var maxId:int = 0;

		public function FigureIDManager(){
			
		}
		/**
		 * Create a new FigureID and return it.
		 */
		public function getAvailabelId():int {
			return ++maxId;
		}
		
		//add by lu 2009-09-24
		public function setAvailabelId(id:int):void{
			this.maxId=id;
		}
	}
}