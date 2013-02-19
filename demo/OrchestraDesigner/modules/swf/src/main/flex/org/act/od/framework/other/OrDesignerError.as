package org.act.od.framework.other{
	
	import mx.resources.IResourceManager;
	import mx.resources.ResourceBundle;
	import mx.resources.ResourceManager;
	import mx.utils.StringUtil;
	
	/**
	 * Error class thrown when a Cairngorm error occurs.
	 * Used to substitute data in error messages.
	 */
	public class OrDesignerError extends Error{
		
		[ResourceBundle("OrDesignerMessages")] 
		private static var rb : ResourceBundle;
	 	
	 	private static var resourceManager :IResourceManager = ResourceManager.getInstance();
		
		public function OrDesignerError( errorCode : String, ... rest ){
			super( formatMessage( errorCode, rest.toString() ) );
		}
		
		private function formatMessage( errorCode : String, ... rest ) : String
		{
			var message :String =  StringUtil.substitute( 
				ResourceManager.getInstance().getString(resourceBundle.bundleName, errorCode ), rest );
			
			return StringUtil.substitute( "{0}: {1}", errorCode, message);
		}
		
		
		protected function get resourceBundle() : ResourceBundle{
			return rb;
		}
		
		
	}
}