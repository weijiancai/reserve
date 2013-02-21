package org.act.od.impl.other
{
	public class StringConvertor
	{
		private static const ISLESSTHAN : String = "<";
		
		private static const ISMORETHAN : String = ">";
		
		private static const ISLESSTHANESCAPE : String = "&lt;";
		
		private static const ISMORETHANESCAPE : String = "&gt;";
		
		public static function readableToEscape(readableString : String) : String {
			
			var escapeString : String = readableString;
			var replace_islessthan_pattern : RegExp = /</g;
			var replace_ismorethan_pattern : RegExp = />/g;
			
			if( replace_islessthan_pattern.test(readableString) )
				escapeString = readableString.replace(replace_islessthan_pattern,ISLESSTHANESCAPE);
			if( replace_ismorethan_pattern.test(readableString) )
				escapeString = readableString.replace(replace_ismorethan_pattern,ISMORETHANESCAPE);
			trace(readableString + "->" + escapeString);
			return escapeString;
		}
		
		public static function escapeToReadable(escapeString : String) : String {
			
			var readableString : String = escapeString;
			var replace_islessthan_pattern : RegExp = /&lt;/g;
			var replace_ismorethan_pattern : RegExp = /&gt;/g;
			
			if( replace_islessthan_pattern.test(escapeString) )
				escapeString.replace(replace_islessthan_pattern,ISLESSTHAN);
			if( replace_islessthan_pattern.test(escapeString) )
				escapeString.replace(replace_ismorethan_pattern,ISMORETHAN);
			trace(escapeString + "->" + readableString);
			return readableString;
		}

	}
}