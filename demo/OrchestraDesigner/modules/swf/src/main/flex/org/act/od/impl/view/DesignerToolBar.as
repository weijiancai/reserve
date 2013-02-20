package org.act.od.impl.view
{
	import flash.events.MouseEvent;

	import mx.containers.ControlBar;
	import mx.containers.HBox;
	import mx.controls.LinkButton;
	import mx.controls.VRule;

	import org.act.od.impl.model.*;
	import org.act.od.impl.other.Localizator;
	import org.act.od.impl.viewhelper.DesignerToolBarVH;
	/**
	 * Tool Bar for OrDesigner.
	 */
	public class DesignerToolBar extends ControlBar
	{
		/**
		 * The source for "newproject" image data binding.
		 */
		[Bindable]
        [Embed(source="/../assets/icon/toolbar/newproject.gif")]
        public var newproject :Class;

		/**
		 * The source for "newfolder" image data binding.
		 */
		[Bindable]
        [Embed(source="/../assets/icon/toolbar/newfolder.gif")]
        public var newfolder :Class;

		/**
		 * The source for "newfile" image data binding.
		 */
		[Bindable]
        [Embed(source="/../assets/icon/toolbar/newfile.gif")]
        public var newfile :Class;

		/**
		 * The source for "save" image data binding.
		 */
        [Bindable]
        [Embed(source="/../assets/icon/toolbar/save.gif")]
        public var save :Class;

		/**
		 * The source for "saveall" image data binding.
		 */
        [Bindable]
        [Embed(source="/../assets/icon/toolbar/saveall.gif")]
        public var saveall :Class;

		/**
		 * The source for "zoomin" image data binding.
		 */
        [Bindable]
        [Embed(source="/../assets/icon/toolbar/zoomin.gif")]
        public var zoomin :Class;

		/**
		 * The source for "zoomout" image data binding.
		 */
        [Bindable]
        [Embed(source="/../assets/icon/toolbar/zoomout.gif")]
        public var zoomout :Class;

		/**
		 * The source for "bpel" image data binding.
		 */
        [Bindable]
        [Embed(source="/../assets/icon/toolbar/bpel.gif")]
        public var bpel :Class;

		/**
		 * The source for "cut" image data binding.
		 */
        [Bindable]
        [Embed(source="/../assets/icon/toolbar/cut.gif")]
        public var cut :Class;

		/**
		 * The source for "copy" image data binding.
		 */
        [Bindable]
        [Embed(source="/../assets/icon/toolbar/copy.gif")]
        public var copy :Class;

		/**
		 * The source for "paste" image data binding.
		 */
        [Bindable]
        [Embed(source="/../assets/icon/toolbar/paste.gif")]
        public var paste :Class;


        /**
         * The source for "configuration" image data binging.
         */
		[Bindable]
		[Embed(source="/../assets/icon/container/uddi.gif")]
		public var configuration :Class;

		private var newProjectButton :LinkButton = new LinkButton();
		private var newFolderButton :LinkButton = new LinkButton();
		private var newFileButton :LinkButton = new LinkButton();
		private var saveButton :LinkButton = new LinkButton();
		private var saveAllButton :LinkButton = new LinkButton();
		private var zoomInButton :LinkButton = new LinkButton();
		private var zoomOutButton :LinkButton = new LinkButton();
		private var bpelButton:LinkButton = new LinkButton();
		private var cutButton :LinkButton = new LinkButton();
		private var copyButton :LinkButton = new LinkButton();
		private var pasteButton :LinkButton = new LinkButton();
		private var configurationButton :LinkButton = new LinkButton();

		private var designerToolBarVH:DesignerToolBarVH;
		private var localizator : Localizator;

		/**
		 * Initialize DesignerToolBar.
		 */
		public function DesignerToolBar() {
			super();
			this.designerToolBarVH = new DesignerToolBarVH(this, "DesignerToolBarVH");
			this.localizator = Localizator.getInstance();
			this.percentHeight = 2;
			this.percentWidth = 100;

			newProjectButton.setStyle("icon",newproject);
			newProjectButton.percentHeight = 100;
			newProjectButton.width = 20;
			newProjectButton.toolTip = localizator.getText('toolbar.newproject');

			newFolderButton.setStyle("icon",newfolder);
			newFolderButton.percentHeight = 100;
			newFolderButton.width = 20;
			newFolderButton.toolTip = localizator.getText('toolbar.newfolder');

			newFileButton.setStyle("icon",newfile);
			newFileButton.percentHeight = 100;
			newFileButton.width = 20;
			newFileButton.toolTip = localizator.getText('toolbar.newfile');

			saveButton.setStyle("icon",save);
			saveButton.percentHeight = 100;
			saveButton.width = 20;
			saveButton.toolTip = localizator.getText('toolbar.save');

			saveAllButton.setStyle("icon",saveall);
			saveAllButton.percentHeight = 100;
			saveAllButton.width = 20;
			saveAllButton.toolTip = localizator.getText('toolbar.saveall');

			zoomInButton.setStyle("icon",zoomin);
			zoomInButton.width = 20;
			zoomInButton.percentHeight = 100;
			zoomInButton.toolTip = localizator.getText('toolbar.zoomin');

			zoomOutButton.setStyle("icon",zoomout);
			zoomOutButton.width = 20;
			zoomOutButton.percentHeight = 100;
			zoomOutButton.toolTip = localizator.getText('toolbar.zoomout');

			bpelButton.setStyle("icon",bpel);
			bpelButton.width = 20;
			bpelButton.percentHeight = 100;
			bpelButton.toolTip = localizator.getText('toolbar.createbpel');

			cutButton.setStyle("icon",cut);
			cutButton.width = 20;
			cutButton.percentHeight = 100;
			cutButton.toolTip = localizator.getText('toolbar.cut');

			copyButton.setStyle("icon",copy);
			copyButton.width = 20;
			copyButton.percentHeight = 100;
			copyButton.toolTip = localizator.getText('toolbar.copy');

			pasteButton.setStyle("icon",paste);
			pasteButton.width = 20;
			pasteButton.percentHeight = 100;
			pasteButton.toolTip = localizator.getText('toolbar.paste');

			configurationButton.setStyle("icon",configuration);
			configurationButton.width = 20;
			configurationButton.percentHeight = 100;
			configurationButton.toolTip = localizator.getText('toolbar.configuration');

			//layout
			var newBox :HBox = new HBox();
			newBox.percentHeight = 100;
			newBox.width = 80;
			newBox.addChild(newProjectButton);
			newBox.addChild(newFolderButton);
			newBox.addChild(newFileButton);

			var saveBox :HBox = new HBox();
			saveBox.percentHeight = 100;
			saveBox.width = 50;
			saveBox.addChild(saveButton);
			saveBox.addChild(saveAllButton);

			var zoomBox :HBox = new HBox();
			zoomBox.percentHeight = 100;
			zoomBox.width = 50;
			zoomBox.addChild(zoomInButton);
			zoomBox.addChild(zoomOutButton);

			var editBox :HBox = new HBox();
			editBox.percentHeight = 100;
			editBox.width = 80;
			editBox.addChild(cutButton);
			editBox.addChild(copyButton);
			editBox.addChild(pasteButton);

			var vrule1 :VRule = new VRule();
			vrule1.percentHeight = 100;

			var vrule2 :VRule = new VRule();
			vrule2.percentHeight = 100;

			var vrule3 :VRule = new VRule();
			vrule3.percentHeight = 100;

			var vrule4:VRule = new VRule();
			vrule4.percentHeight = 100;

			var vrule5:VRule = new VRule();
			vrule5.percentHeight = 100;

			this.setStyle("borderStyle", "solid");

			this.addChild(newBox);
			this.addChild(vrule1);
			this.addChild(saveBox);
			this.addChild(vrule2);
			this.addChild(editBox);
			this.addChild(vrule3);
			this.addChild(zoomBox);
			this.addChild(vrule4);
			this.addChild(configurationButton);
			this.addChild(vrule5);
			this.addChild(bpelButton);

			this.initEventListener();
		}
		/**
		 * Initialize the listeners of all button.
		 */
		private function initEventListener():void{
			this.newProjectButton.addEventListener(MouseEvent.CLICK, designerToolBarVH.newProjectHandler);
//			this.newFolderButton.addEventListener(MouseEvent.CLICK, designerToolBarVH.newFolderHandler);
//			this.newFileButton.addEventListener(MouseEvent.CLICK, designerToolBarVH.newFileHandler);

//			this.saveButton.addEventListener(MouseEvent.CLICK, designerToolBarVH.onSaveHandler);
//			this.saveAllButton.addEventListener(MouseEvent.CLICK, designerToolBarVH.onSaveAllHandler);

//			this.copyButton.addEventListener(MouseEvent.CLICK, designerToolBarVH.onCopyHandler);
//			this.pasteButton.addEventListener(MouseEvent.CLICK, designerToolBarVH.onPasteHandler);
//			this.cutButton.addEventListener(MouseEvent.CLICK, designerToolBarVH.onCutHandler);

//			this.zoomInButton.addEventListener(MouseEvent.CLICK, designerToolBarVH.onZoomInHandler);
//			this.zoomOutButton.addEventListener(MouseEvent.CLICK, designerToolBarVH.onZoomOutHandler);

//			this.configurationButton.addEventListener(MouseEvent.CLICK, designerToolBarVH.onDUUIConfigurationHandle);

//			this.bpelButton.addEventListener(MouseEvent.CLICK, designerToolBarVH.onBpelHandler);
		}
	}
}