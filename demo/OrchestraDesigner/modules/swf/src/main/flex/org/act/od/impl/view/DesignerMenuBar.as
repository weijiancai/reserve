package org.act.od.impl.view{
	
	import mx.collections.XMLListCollection;
	import mx.controls.MenuBar;
	import mx.events.MenuEvent;
	
	import org.act.od.impl.other.Localizator;
	import org.act.od.impl.viewhelper.DesignerMenuBarVH;
	
	/**
	 * Menu Bar for OrDesigner.
	 */
	public class DesignerMenuBar extends MenuBar{

        private var designerMenuBarVH :DesignerMenuBarVH;
		
		/**
		 * Initialize DesignerMenuBar.
		 */
		public function DesignerMenuBar(){
			//view component
			super();
			
			this.labelField = "@label";
			this.dataProvider = new XMLListCollection(menubarXML.menuitem);
			this.percentWidth = 100;
			designerMenuBarVH = new DesignerMenuBarVH(this, DesignerMenuBarVH.VH_KEY);
			this.initEventListener();
			
		}
		private function initEventListener():void{
			
        	var localizator : Localizator = Localizator.getInstance();
			for each(var item : XML in menubarXML..menuitem){
				var labelText : String = null;
				if(item.@id == "0") {
					labelText = localizator.getText('menubar.file');
					item.@label = labelText;
				}
                else if(item.@id == "1") {
                    labelText = localizator.getText('menubar.file.new');
                    item.@label = labelText;
                }
				else if(item.@id == "2") {
					labelText = localizator.getText('menubar.file.project');
					item.@label = labelText;
				}
				else if(item.@id == "3") {
					labelText = localizator.getText('menubar.file.folder');
					item.@label = labelText;
				}
				else if(item.@id == "4") {
					labelText = localizator.getText('menubar.file.file');
					item.@label = labelText;
				}
				else if(item.@id == "5") {
					labelText = localizator.getText('menubar.file.close');
					item.@label = labelText;
				}
				else if(item.@id == "6") {
					labelText = localizator.getText('menubar.file.closeall');
					item.@label = labelText;
				}
				else if(item.@id == "7") {
					labelText = localizator.getText('menubar.file.save');
					item.@label = labelText;
				}
				else if(item.@id == "8") {
					labelText = localizator.getText('menubar.file.saveall');
					item.@label = labelText;
				}
                else if(item.@id == "9") {
                    labelText = localizator.getText('menubar.edit');
                    item.@label = labelText;
                }
				else if(item.@id == "10") {
					labelText = localizator.getText('menubar.edit.cut');
					item.@label = labelText;
				}
				else if(item.@id == "11") {
					labelText = localizator.getText('menubar.edit.copy');
					item.@label = labelText;
				}
				else if(item.@id == "12") {
					labelText = localizator.getText('menubar.edit.paste');
					item.@label = labelText;
				}
				else if(item.@id == "13") {
					labelText = localizator.getText('menubar.edit.selectall');
					item.@label = labelText;
				}
                else if(item.@id == "14") {
                    labelText = localizator.getText('menubar.bpel');
                    item.@label = labelText;
                }
				else if(item.@id == "15") {
					labelText = localizator.getText('menubar.bpel.createbpel');
					item.@label = labelText;
				}
                else if(item.@id == "16") {
                    labelText = localizator.getText('munubar.uddi');
                    item.@label = labelText;
                }
                else if(item.@id == "17") {
                    labelText = localizator.getText('menubar.uddi.configuration');
                    item.@label = labelText;
                }
                else if(item.@id == "18") {
                    labelText = localizator.getText('menubar.help');
                    item.@label = labelText;
                }
				else if(item.@id == "19") {
					labelText = localizator.getText('menubar.help.about');
					item.@label = labelText;
				}
			}
			this.addEventListener(MenuEvent.ITEM_CLICK, designerMenuBarVH.menuClick);
		}
		//data
		private var menubarXML :XML =
			<menus>
				<menuitem label="File" icon="newproject" id="0">
					<menuitem label="New" icon="newproject" id="1">
						<menuitem label="Project" icon="{newproject}" id="2"/>
						<menuitem label="Folder" icon="" id="3"/>
						<menuitem label="File" icon="" id="4"/>
					</menuitem>
					<menuitem type="separator" icon=""/>
					<menuitem label="Close" icon="" id="5"/>
					<menuitem label="Close All" icon="" id="6"/>
					<menuitem type="separator" icon="" />
					<menuitem label="Save" icon="" id="7"/>
					<menuitem label="Save All" icon="" id="8"/>
				</menuitem>
				
				<menuitem label="Edit" icon="{newproject}" id="9">
					<menuitem label="Cut" icon="" id="10"/>
					<menuitem label="Copy" icon="" id="11"/>
					<menuitem label="Paste" icon="" id="12"/>
					<menuitem type="separator" icon="" />
					<menuitem label="Select All" icon="" id="13"/>
				</menuitem>

				<menuitem label="BPEL" icon="{newproject}" id="14">
					<menuitem label="Create BPEL"  icon="" id="15"/>
				</menuitem>
				
				<menuitem label="UDDI" icon="{newproject}" id="16">
					<menuitem label="Configuration"  icon="" id="17"/>
				</menuitem>
				
				<menuitem label="Help" icon="{newproject}" id="18">
					<menuitem label="About Orchestra Designer"  icon="" id="19"/>
				</menuitem>
			</menus>;
	}
}