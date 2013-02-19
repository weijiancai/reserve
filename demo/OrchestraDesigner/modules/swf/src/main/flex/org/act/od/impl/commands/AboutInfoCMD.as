package org.act.od.impl.commands
{
	import mx.managers.PopUpManager;
	
	import org.act.od.framework.commands.AODCommand;
	import org.act.od.framework.control.OrDesignerEvent;
	import org.act.od.impl.model.OrDesignerModelLocator;
	import org.act.od.impl.view.AboutWindow;
	
	/**
	 * Show the "About Orchestra Designer" Window.
	 */ 
	public class AboutInfoCMD extends AODCommand
	{
		public function AboutInfoCMD(){
			super();
		}
		
		override public function execute(event:OrDesignerEvent):void{
			var aboutWindow:AboutWindow = AboutWindow(PopUpManager.createPopUp(OrDesignerModelLocator.getInstance().getOrchestraDesigner(),AboutWindow,true));
			PopUpManager.centerPopUp(aboutWindow);
		}
	}
}