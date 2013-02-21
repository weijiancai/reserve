package org.act.od.impl.vo
{
	import mx.collections.ArrayCollection;

	/**
	 * The IAttribute interface provide the abstract method about figures' attribute.
	 */
	public interface IAttribute
	{
		/**
		 * Return the attributeArray about the selected figure.
		 */
		function getAttributeArray():ArrayCollection;

		/**
		 * Updata the attributeArray.
		 */
		function update(atts:ArrayCollection):void;

		/**
		 * Chang the attributeArray into xml and store them in a array.
		 */
		function getAttributeXML():Array;

		/**
		 * Update the xml array whitch store the arrtibute.
		 */
		function setAttribute(atts:XMLList):void;
	}
}