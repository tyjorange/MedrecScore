package data.progress;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import data.dao.pojo.Medrec;
import data.dao.pojo.MedrecScore;
import data.progress.rule.Rule;
import data.progress.rule.Rule.CostInfo;
import data.progress.rule.RuleLimit;
import data.progress.score.BaseInfoScore;
import data.progress.score.CostInfoScore;
import data.progress.score.DiagnosisInfoScore;
import data.progress.score.HospitalInfoScore;

/**
 * @author tyj
 *
 *         2018年7月12日 - 下午3:24:02
 */
public class Main {
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	private final static Double SCORE = 100d;// 总分
	private final static Double ZERO_SCORE = 0d;// 总分
	private final static String TYPE = "4";// 入院病情N 4=无

	/**
	 * 
	 * @param list
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public static List<MedrecScore> progress(List<Medrec> list) {
		ArrayList<MedrecScore> arrayList = new ArrayList<MedrecScore>();
		for (Medrec m : list) {
			MedrecScore ms = new MedrecScore();
			ms.setScore(new BigDecimal(SCORE));
			try {
				initRule(m);
				ms = infoCount(Rule.BaseInfo.class, BaseInfoScore.class, m, ms);
				ms = infoCount(Rule.HospitalInfo.class, HospitalInfoScore.class, m, ms);
				ms = infoCount(Rule.DiagnosisInfo.class, DiagnosisInfoScore.class, m, ms);
				ms = infoCountCost(Rule.CostInfo.class, CostInfoScore.class, m, ms);
			} catch (NoSuchFieldException | SecurityException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			ms.setId(m.getId());
			ms.setCode(m.getCode());
			if (m.getMrYear() != null)
				ms.setMrYear(sdf.format(m.getMrYear()));
			arrayList.add(ms);
		}
		return arrayList;
	}

	/**
	 * 初始化计分规则
	 * 
	 * @param m
	 *            数据项
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static void initRule(Medrec m) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// 其他诊断
		ArrayList<String> listCa3 = new ArrayList<String>();
		ArrayList<String> listCa4 = new ArrayList<String>();
		for (String[] g : Rule.G1) {
			Object v0 = getDataValue(m, getDataField(m, g[0]));
			Object v1 = getDataValue(m, getDataField(m, g[1]));
			Object v2 = getDataValue(m, getDataField(m, g[2]));
			if (v0 != null | v1 != null | v2 != null) {
				if (!(TYPE.equals((String) v2))) {// 入院病情N 4=无
					listCa3.add(g[0]);
					listCa4.add(g[1]);
				}
			}
		}
		Rule.DiagnosisInfo.ca3 = listCa3.toArray(new String[listCa3.size()]);
		Rule.DiagnosisInfo.ca4 = listCa4.toArray(new String[listCa4.size()]);

		// 外部原因
		ArrayList<String> listCd1 = new ArrayList<String>();
		for (String[] g : Rule.G2) {
			Object v0 = getDataValue(m, getDataField(m, g[0]));
			Object v1 = getDataValue(m, getDataField(m, g[1]));
			if (v0 != null | v1 != null) {
				listCd1.add(g[0]);
				listCd1.add(g[1]);
			}
		}
		for (String s : Rule.DiagnosisInfo.cd1) {
			listCd1.add(s);
		}

		// 病理
		ArrayList<String> listCb2 = new ArrayList<String>();
		ArrayList<String> listCb3 = new ArrayList<String>();
		for (String[] g : Rule.G3) {
			Object v0 = getDataValue(m, getDataField(m, g[0]));
			Object v1 = getDataValue(m, getDataField(m, g[1]));
			Object v2 = getDataValue(m, getDataField(m, g[2]));
			if (v0 != null | v1 != null | v2 != null) {
				listCb2.add(g[0]);
				listCd1.add(g[1]);
				listCb3.add(g[2]);
			}
		}
		Rule.DiagnosisInfo.cb2 = listCb2.toArray(new String[listCb2.size()]);
		Rule.DiagnosisInfo.cb3 = listCb3.toArray(new String[listCb3.size()]);

		// 主要手术
		ArrayList<String> listCa5 = new ArrayList<String>();
		ArrayList<String> listCa6 = new ArrayList<String>();
		ArrayList<String> listCb4 = new ArrayList<String>();
		ArrayList<String> listCb8 = new ArrayList<String>();
		ArrayList<String> listCc3 = new ArrayList<String>();
		for (String[] g : Rule.G4) {
			Object v0 = getDataValue(m, getDataField(m, g[0]));
			Object v1 = getDataValue(m, getDataField(m, g[1]));
			Object v2 = getDataValue(m, getDataField(m, g[2]));
			Object v3 = getDataValue(m, getDataField(m, g[3]));
			Object v4 = getDataValue(m, getDataField(m, g[4]));
			Object v5 = getDataValue(m, getDataField(m, g[5]));
			Object v6 = getDataValue(m, getDataField(m, g[6]));
			Object v7 = getDataValue(m, getDataField(m, g[7]));
			Object v8 = getDataValue(m, getDataField(m, g[8]));
			Object v9 = getDataValue(m, getDataField(m, g[9]));
			Object v10 = getDataValue(m, getDataField(m, g[10]));
			if (v0 != null | v1 != null | v2 != null | v3 != null | v4 != null | v5 != null | v6 != null | v7 != null | v8 != null | v9 != null | v10 != null) {
				listCa5.add(g[3]);
				listCa6.add(g[0]);
				listCb4.add(g[7]);
				listCb4.add(g[8]);
				listCb8.add(g[1]);
				listCc3.add(g[9]);
				listCd1.add(g[2]);
				listCd1.add(g[4]);
				listCd1.add(g[5]);
			}
		}
		Rule.DiagnosisInfo.ca5 = listCa5.toArray(new String[listCa5.size()]);
		Rule.DiagnosisInfo.ca6 = listCa6.toArray(new String[listCa6.size()]);
		Rule.DiagnosisInfo.cb4 = listCb4.toArray(new String[listCb4.size()]);
		Rule.DiagnosisInfo.cb8 = listCb8.toArray(new String[listCb8.size()]);
		Rule.DiagnosisInfo.cc3 = listCc3.toArray(new String[listCc3.size()]);
		Rule.DiagnosisInfo.cd1 = listCd1.toArray(new String[listCd1.size()]);

		// 其他手术
		ArrayList<String> listCb6 = new ArrayList<String>();
		ArrayList<String> listCb7 = new ArrayList<String>();
		for (String[] g : Rule.G5) {
			Object v0 = getDataValue(m, getDataField(m, g[0]));
			Object v1 = getDataValue(m, getDataField(m, g[1]));
			Object v2 = getDataValue(m, getDataField(m, g[2]));
			Object v3 = getDataValue(m, getDataField(m, g[3]));
			Object v4 = getDataValue(m, getDataField(m, g[4]));
			Object v5 = getDataValue(m, getDataField(m, g[5]));
			Object v6 = getDataValue(m, getDataField(m, g[6]));
			Object v7 = getDataValue(m, getDataField(m, g[7]));
			Object v8 = getDataValue(m, getDataField(m, g[8]));
			Object v9 = getDataValue(m, getDataField(m, g[9]));
			Object v10 = getDataValue(m, getDataField(m, g[10]));
			if (v0 != null | v1 != null | v2 != null | v3 != null | v4 != null | v5 != null | v6 != null | v7 != null | v8 != null | v9 != null | v10 != null) {
				listCb6.add(g[3]);
				listCb7.add(g[0]);
			}
		}
		Rule.DiagnosisInfo.cb6 = listCb6.toArray(new String[listCb6.size()]);
		Rule.DiagnosisInfo.cb7 = listCb7.toArray(new String[listCb7.size()]);
	}

	/**
	 * 统计分数
	 * 
	 * @param ruleClazz
	 *            统计规则
	 * @param scoreClazz
	 *            单项积分
	 * @param medrec
	 *            数据项
	 * @param medrecScore
	 *            结果数据项
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private static MedrecScore infoCount(Class<?> ruleClazz, Class<?> scoreClazz, Medrec medrec, MedrecScore medrecScore) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
		double total = medrecScore.getScore().doubleValue();
		Field[] fields = ruleClazz.getDeclaredFields();
		for (Field ruleField : fields) {
			// System.out.println(ruleField.getName());
			String[] ruleArray = getRule(ruleClazz, ruleField);
			if (ruleArray.length > 1) {// 多项
				double limit = getRuleLimit(ruleField.getName());
				double temp = ZERO_SCORE;
				for (String s : ruleArray) {
					Field dataField = getDataField(medrec, s);
					Object dataValue = getDataValue(medrec, dataField);
					if (dataValue == null) {
						double socer = getSocer(scoreClazz, dataField);
						temp += socer;
						if (temp >= limit) {
							break;
						}
					}
				}
				medrecScore = setResult(medrecScore, ruleField.getName(), temp);
				total -= temp;
			} else if (ruleArray.length == 1) { // 单项
				Field dataField = getDataField(medrec, ruleArray[0]);
				Object dataValue = getDataValue(medrec, dataField);
				if (dataValue == null) {
					double socer = getSocer(scoreClazz, dataField);
					medrecScore = setResult(medrecScore, ruleField.getName(), socer);
					total -= socer;
				} else {
					medrecScore = setResult(medrecScore, ruleField.getName(), new Double(0));
				}
			} else if (ruleArray.length == 0) {// 空项
				medrecScore = setResult(medrecScore, ruleField.getName(), new Double(0));
			}
		}
		medrecScore.setScore(new BigDecimal(total));
		return medrecScore;
	}

	/**
	 * 统计费用分数
	 * 
	 * @param ruleClazz
	 *            费用统计规则
	 * @param scoreClazz
	 *            费用单项积分
	 * @param medrec
	 *            数据项
	 * @param medrecScore
	 *            结果数据项
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private static MedrecScore infoCountCost(Class<CostInfo> ruleClazz, Class<CostInfoScore> scoreClazz, Medrec medrec, MedrecScore medrecScore) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		double total = medrecScore.getScore().doubleValue();
		final String DA1 = "da1";
		final String DD_ = "dd_";
		final String DD1 = "dd1";
		// da1统计
		String[] ruleDa1 = getRule(ruleClazz, ruleClazz.getDeclaredField(DA1));
		Field da1Field = getDataField(medrec, ruleDa1[0]);
		Object da1Value = getDataValue(medrec, da1Field);
		if (da1Value == null) {
			double socer = getSocer(scoreClazz, da1Field);
			medrecScore = setResult(medrecScore, DA1, socer);
			total -= socer;
		} else {
			medrecScore = setResult(medrecScore, DA1, new Double(0));
		}
		// dd1 统计前判断
		String[][] ruleDd_ = getRuleCost(ruleClazz, ruleClazz.getDeclaredField(DD_));
		for (String[] ruleArray_ : ruleDd_) {
			Field totalField = getDataField(medrec, "c204");// 获取总费用字段
			BigDecimal totalsVar = (BigDecimal) getDataValue(medrec, totalField);// 获取总费用值
			if (totalsVar == null) {// 无费用信息
				medrecScore = setResult(medrecScore, DD1, ZERO_SCORE);
				medrecScore.setScore(new BigDecimal(total));
				System.out.println(medrec.getId() + " Column 'c204' is null, continue");
				continue;
			}
			BigDecimal temp_ = new BigDecimal(0);
			Field dd1Field_ = null;
			for (String s : ruleArray_) {// 统计费用和是否等于总费用
				dd1Field_ = getDataField(medrec, s);
				BigDecimal dd1Value_ = (BigDecimal) getDataValue(medrec, dd1Field_);
				if (dd1Value_ != null) {
					temp_ = temp_.add(dd1Value_);
				}
			}
			// 费用和是否等于总费用
			if (totalsVar.doubleValue() == temp_.doubleValue()) {
				medrecScore = setResult(medrecScore, DD1, ZERO_SCORE);
				medrecScore.setScore(new BigDecimal(total));
			} else {
				// dd1统计
				String[][] ruleDd1 = getRuleCost(ruleClazz, ruleClazz.getDeclaredField(DD1));
				double temp = ZERO_SCORE;
				double limit = getRuleLimit(DD1);
				for (String[] ruleArray : ruleDd1) {
					if (ruleArray.length > 1) {// 多项
						int count = 0;// 统计null数
						Field dd1Field = null;
						for (String s : ruleArray) {// 统计费用组是否有null
							dd1Field = getDataField(medrec, s);
							Object dd1Value = getDataValue(medrec, dd1Field);
							if (dd1Value == null) {
								count++;
							}
						}
						if (count != ruleArray.length & count != 0) {// 费用组不全null或至少有1个null
							double socer = getSocer(scoreClazz, dd1Field);
							temp += socer;
						}
					} else if (ruleArray.length == 1) {// 单项
						Field dd1Field = getDataField(medrec, ruleArray[0]);
						Object dd1Value = getDataValue(medrec, dd1Field);
						if (dd1Value == null) {
							double socer = getSocer(scoreClazz, dd1Field);
							temp += socer;
						}
					}
					if (temp >= limit) {
						break;
					}
				}
				medrecScore = setResult(medrecScore, DD1, temp);
				total -= temp;
				medrecScore.setScore(new BigDecimal(total));
			}
		}
		return medrecScore;
	}

	/**
	 * 获取计分规则
	 * 
	 * @param ruleClazz
	 * 
	 * @param ruleField
	 *            规则字段
	 * @return 统计规则字段数组
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static String[] getRule(Class<?> ruleClazz, Field ruleField) throws IllegalArgumentException, IllegalAccessException {
		ruleField.setAccessible(true);
		return (String[]) ruleField.get(ruleClazz);
	}

	/**
	 * 获取费用计分规则
	 * 
	 * @param ruleClazz
	 * @param ruleField
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static String[][] getRuleCost(Class<?> ruleClazz, Field ruleField) throws IllegalArgumentException, IllegalAccessException {
		ruleField.setAccessible(true);
		return (String[][]) ruleField.get(ruleClazz);
	}

	/**
	 * 获取积分规则减分上限
	 * 
	 * @param ruleFieldName
	 *            字段名
	 * @return 上限分值
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static double getRuleLimit(String ruleFieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field ruleLimitField = RuleLimit.class.getDeclaredField(ruleFieldName);
		ruleLimitField.setAccessible(true);
		return (double) ruleLimitField.get(RuleLimit.class);
	}

	/**
	 * 获取数据字段对象
	 * 
	 * @param medrec
	 *            数据对象
	 * @param s
	 *            字段名
	 * @return 数据字段对象
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static Field getDataField(Medrec medrec, String s) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		return medrec.getClass().getDeclaredField(s);
	}

	/**
	 * 获取数据值
	 * 
	 * @param medrec
	 *            数据对象
	 * @param dataField
	 *            数据字段对象
	 * @return 数据值对象 or null
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static Object getDataValue(Medrec medrec, Field dataField) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		dataField.setAccessible(true);
		Object dataValue = dataField.get(medrec);
		// System.out.println("	" + dataField.getName() + "=" + dataValue);
		return dataValue;
	}

	/**
	 * 获取字段单项分值
	 * 
	 * @param scoreClazz
	 * @param dataField
	 *            字段对象
	 * @return 单项分值
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static double getSocer(Class<?> scoreClazz, Field dataField) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field socer_field = scoreClazz.getDeclaredField(dataField.getName());
		socer_field.setAccessible(true);
		return (double) socer_field.get(scoreClazz);
	}

	/**
	 * 设置结果数据指定字段分值
	 * 
	 * @param medrecScore
	 *            结果数据对象
	 * @param fieldName
	 *            字段
	 * @param dValue
	 *            分值
	 * @return 设置完的结果对象
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static MedrecScore setResult(MedrecScore medrecScore, String fieldName, Double dValue) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = medrecScore.getClass().getDeclaredField(fieldName);
		f.setAccessible(true);
		f.set(medrecScore, new BigDecimal(-dValue));
		return medrecScore;
	}
}
