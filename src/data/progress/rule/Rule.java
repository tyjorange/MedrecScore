package data.progress.rule;

/**
 * 计分规则
 * 
 * @author tyj
 *
 *         2018年7月12日 - 上午9:27:46
 */
public class Rule {
	public static class BaseInfo {
		final static String[] aa1 = { "c14" };
		final static String[] aa2 = { "c13" };
		final static String[] ab1 = { "c6" };
		final static String[] ac1 = { "c8" };
		final static String[] ac2 = { "c9" };
		final static String[] ac3 = { "c10" };
		final static String[] ac4 = { "c3" };
		final static String[] ad1 = { "c4", "c7", "c15", "c16", "c18", "c19", "c20", "c21", "c22", "c23", "c24", "c25", "c26", "c27", "c28", "c29", "c30", "c31", "c32" };
	}

	public static class HospitalInfo {
		final static String[] ba1 = { "c193" };
		final static String[] bb1 = { "c34" };
		final static String[] bb2 = { "c39" };
		final static String[] bb3 = { "c43" };
		final static String[] bb4 = { "c44" };
		final static String[] bb5 = { "c196" };
		final static String[] bc1 = { "c33" };
		final static String[] bc2 = { "c37" };
		final static String[] bc3 = {  };
//		final static String[] bc3 = { "c38" };
	}

	public final static String[][] G1 = { 
		{ "c49", "c50", "c51" }, { "c52", "c53", "c54" }, { "c55", "c56", "c57" }, 
		{ "c58", "c59", "c60" }, { "c61", "c62", "c63" }, { "c64", "c65", "c66" }, 
		{ "c67", "c68", "c69" }, { "c70", "c71", "c72" }, { "c73", "c74", "c75" },
		{ "c76", "c77", "c78" }, { "c79", "c80", "c81" }, { "c82", "c83", "c84" }, 
		{ "c85", "c86", "c87" }, { "c88", "c89", "c90" }, { "c91", "c92", "c93" } 
	};
	
	public final static String[][] G2 = {
		{ "c94", "c95" }
	};
	
	public final static String[][] G3 = { 
		{ "c96", "c97", "c98" }
	};
	
	public final static String[][] G4 = { 
		{ "c116", "c117", "c118", "c119", "c120", "c121", "c122", "c123", "c124", "c125", "c126" }
	};
	
	public final static String[][] G5 = { 
		{ "c127", "c128", "c129", "c130", "c131", "c132", "c133", "c134", "c135", "c136", "c137" }, 
		{ "c138", "c139", "c140", "c141", "c142", "c143", "c144", "c145", "c146", "c147", "c148" },
		{ "c149", "c150", "c151", "c152", "c153", "c154", "c155", "c156", "c157", "c158", "c159" }, 
		{ "c160", "c161", "c162", "c163", "c164", "c165", "c166", "c167", "c168", "c169", "c170" },
		{ "c171", "c172", "c173", "c174", "c175", "c176", "c177", "c178", "c179", "c180", "c181" }, 
		{ "c182", "c183", "c184", "c185", "c186", "c187", "c188", "c189", "c190", "c191", "c192" } 
	};
	

	public static class DiagnosisInfo {
		final static String[] ca1 = { "c46" };
		final static String[] ca2 = { "c47" };
		public static String[] ca3 = {  };
		public static String[] ca4 = {  };
		public static String[] ca5 = {  };
//		final static String[] ca5 = { "c119" };
		public static String[] ca6 = {  };
//		final static String[] ca6 = { "c116" };
		final static String[] cb1 = { "c48" };
		public static String[] cb2 = {  };
//		final static String[] cb2 = { "c96" };
		public static String[] cb3 = {  };
//		final static String[] cb3 = { "c98" };
		public static String[] cb4 = {  };
//		final static String[] cb4 = { "c123", "c124" };
		final static String[] cb5 = {  }; //"c198" 昏迷时间
		public static String[] cb6 = {  };
		public static String[] cb7 = {  };
		public static String[] cb8 = {  };
//		final static String[] cb8 = { "c117" };
		final static String[] cc1 = { "c44" };
		final static String[] cc2 = { "c45" };
		public static String[] cc3 = {  };
//		final static String[] cc3 = { "c125" };
		public static String[] cd1 = { "c99", "c101", "c102", "c103" };
//		final static String[] cd1 = { "c94", "c95", "c97", "c99", "c101", "c102", "c103", "c118", "c120", "c121" };
	}

	public static class CostInfo {
		final static String[] da1 = { "c204" };
		final static String[][] dd_ = {
			{ "c206", "c207", "c208", "c209", "c210", "c211", "c212", "c213",
			  "c214", "c216", "c219", "c220", "c221", "c223", "c224", "c225", 
			  "c226", "c227", "c228", "c229", "c230", "c231", "c232", "c233" }
		};
		final static String[][] dd1 = {
			{ "c206", "c207", "c208", "c209" }, 
			{ "c210", "c211", "c212", "c213" }, 
			{ "c214", "c215", "c216", "c217", "c218" }, 
			{ "c219" }, 
			{ "c220" }, 
			{ "c221", "c222" }, 
			{ "c223", "c224" }, 
			{ "c225", "c226", "c227", "c228", "c229" },
			{ "c230", "c231", "c232" },
			{ "c233" } 
		};
	}
}
