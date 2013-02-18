package org.act.od.impl.other
{
	import flash.events.Event;
	import flash.events.EventDispatcher;

	import mx.resources.ResourceBundle;
	import mx.resources.ResourceManager;

	public class Localizator extends EventDispatcher
	{

		private static var _instance : Localizator;

		public static const BPMN_RESOURCE_BUNDLE : String = "BPMN";
		public static const BPEL_RESOURCE_BUNDLE : String = "BPEL";

		private var _language : String;

		private var _type : String;

		//resource.properties
		[ResourceBundle("OrDesigner")]
		private var lang_en_US : ResourceBundle;

		[ResourceBundle("BpmnOrDesigner")]
		private var bpmnResourceBundele : ResourceBundle;

		[Bindable]
		private var currRes : ResourceBundle;

		public function Localizator(language : String = "en_US", resourceBundleType : String = BPEL_RESOURCE_BUNDLE) {
			selectLanguage(language,resourceBundleType);
		}

		public static function getInstance(language : String = "en_US", resourceBundleType : String = BPEL_RESOURCE_BUNDLE):Localizator {
			if (_instance == null || _instance._type != resourceBundleType) {
				_instance = new Localizator(language,resourceBundleType);
			}
			return _instance;
		}

		private function selectLanguage(language : String, resourceBundleType : String = BPEL_RESOURCE_BUNDLE):void {
			this._language = language;

			if (_language == "en_US")
			{
				this._type = resourceBundleType;
				if(resourceBundleType == Localizator.BPMN_RESOURCE_BUNDLE)
					this.currRes = bpmnResourceBundele;
				else
					this.currRes = lang_en_US;
			}
			else
			{
				this._type = resourceBundleType;
				if(resourceBundleType == Localizator.BPMN_RESOURCE_BUNDLE)
					this.currRes = bpmnResourceBundele;
				else
					this.currRes = lang_en_US;
			}
		}

		[Bindable(event="languageChange")]
		public function getText(key : String):String {

			return ResourceManager.getInstance().getString(this.currRes.bundleName,key);
		}

		public function get language():String {
			return this._language;
		}

		public function set language(language : String ):void {
			if (this._language != language) {
				selectLanguage(language);
				dispatchEvent(new Event("languageChange"));
			}
		}


	}


}