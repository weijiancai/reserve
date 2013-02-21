package org.act.od.impl.vo
{
	import mx.collections.ArrayCollection;

	import org.act.od.impl.other.StringConvertor;
	/**
	 * The attribute about basic figures.
	 */
	public class BasicAttribute implements IAttribute
	{
		[Bindable]
		public var type:String;

		[Bindable]
		public var name:String;

		[Bindable]
		public var id:int;

		public function BasicAttribute():void {
			this.type = "none";
			this.name = "";
			this.id = -1;
		}

		public function getAttributeArray():ArrayCollection {

			var atts:ArrayCollection = new ArrayCollection();
			atts.addItem({name: "Id: ", value: id, flag: "false"});
			atts.addItem({name: "Type: ", value: type, flag: "false"});
			atts.addItem({name: "Name: ", value: name, flag: "true"});
			return atts;
		}

		public function update(atts:ArrayCollection):void {
			for each(var att:Object in atts) {
				if(att.name == "Name: ") {
					this.name = att.value;
				}
				else if(att.name == "Id: ") {
					this.id = this.id;
				}
				else if(att.name == "Type: ") {
					this.type = this.type;
				}
			}
		}

		public function getAttributeXML():Array {
			var xmlArray:Array = new Array();
			var idXMLValue : String = StringConvertor.readableToEscape(id.toString());
			var typeXMLValue : String = StringConvertor.readableToEscape(type);
			var nameXMLValue : String = StringConvertor.readableToEscape(name);

			var idNode:XML = new XML("<Attribute></Attribute>");
			idNode.@name = "id";
//			idNode.@value = this.id;
			idNode.appendChild(idXMLValue);

			var typeNode:XML = new XML("<Attribute></Attribute>");
			typeNode.@name = "type";
//			typeNode.@text = this.type;
			typeNode.appendChild(typeXMLValue);

			var nameNode:XML = new XML("<Attribute></Attribute>");
			nameNode.@name = "name";
//			nameNode.@text = this.name;
			nameNode.appendChild(nameXMLValue);

			xmlArray.push(idNode,typeNode,nameNode);
			return xmlArray;
		}

		public function setAttribute(atts:XMLList):void {
			var nameNode:XMLList = atts.Attribute.(@name == "name");
			var idNode:XMLList = atts.Attribute.(@name == "id");
			var typeNode:XMLList = atts.Attribute.(@name == "type");

			var nameXMLValue : String = (nameNode.children()!=null)?nameNode.children().toString():"";
			var idXMLValue : String = this.id.toString();
			var typeXMLValue : String = this.type;
			var nameValue : String = StringConvertor.escapeToReadable(nameXMLValue);
			var idValue : String = StringConvertor.escapeToReadable(idXMLValue);
			var typeValue : String = StringConvertor.escapeToReadable(typeXMLValue);

			this.name = nameValue;
		}

	}
}