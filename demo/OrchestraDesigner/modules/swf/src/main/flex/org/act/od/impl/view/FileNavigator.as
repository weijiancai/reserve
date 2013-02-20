package org.act.od.impl.view
{
	import mx.containers.Box;
	import mx.containers.TabNavigator;
	
	import org.act.od.impl.other.Localizator;
	
	/**
	 * The parent panel of fileNavigatorView.
	 */
	public class FileNavigator extends TabNavigator
	{
		/**
		 * The source for "navigator" image data binding.
		 */
		[Bindable]
        [Embed(source="/../assets/icon/container/navigator.gif")]
        public var navigator :Class;
		
		private var fileNavigatorView :FileNavigatorView;
		private var localizator :Localizator;
		
		public function FileNavigator()
		{
			super();
			
			this.percentHeight=100;
			this.percentWidth=100;
			
			createNewFigureEditor(this);
		}
		
		private function createNewFigureEditor(tabNavigator :TabNavigator):void{
		
			fileNavigatorView = new FileNavigatorView();
			this.localizator = Localizator.getInstance();
			var contentBox :Box = new Box();
			contentBox.label = localizator.getText('filenavigator.label');
			contentBox.percentHeight = 100;
			contentBox.percentWidth = 100;
			contentBox.setStyle("borderStyle","solid");
			contentBox.icon = navigator;
			contentBox.addChild(fileNavigatorView);
			tabNavigator.addChild(contentBox);
			
		}
		
	}
}