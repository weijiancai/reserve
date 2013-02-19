package org.act.od.impl.model
{
	/**
	 * 
	 */
	public class SingleEnforcer
	{
		private static var num :uint;
		
		public function SingleEnforcer()
		{
			num++;
	    	if (num >= 2) {
	 			throw (new Error("more than one singleton instance"));
	 		}
	 	}
		
	}
}