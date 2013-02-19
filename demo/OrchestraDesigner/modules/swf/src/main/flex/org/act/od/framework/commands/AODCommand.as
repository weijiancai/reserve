package org.act.od.framework.commands{
	import org.act.od.framework.control.OrDesignerEvent;
	
	
	/**
	 * 默认情况下命令是不可回退的，因此isReversible()返回false，Unexecute()方法为空
	 * 
	 * @author lizq
	 * 
	 */
	public class AODCommand implements IODCommand{
		
		public function AODCommand(){
			super();
		}
		
		/**
		 * nead to be overwrite 
		 * @param event
		 * 
		 */
		public function execute(event :OrDesignerEvent) :void{
		}
    
      	public function Unexecute() :void{
      	}
      
      
     	/**
     	 * if return TRUE，then the method Unexecute is required 
     	 * 
     	 * @return 
     	 */
     	public function isReversible() :Boolean{
     		return false;
     	}

	}
}