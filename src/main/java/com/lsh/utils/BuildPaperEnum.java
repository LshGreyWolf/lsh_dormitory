package com.lsh.utils;

public enum BuildPaperEnum {
    dayTest(BizKeyEnum.dayTest.getbiz(), "buildDayTestPaper"),
    pointPractice(BizKeyEnum.pointPractice.getbiz(), "buildPointPracticePaper"),
    oneTreatOne(BizKeyEnum.oneTreatOne.getbiz(), "buildOneTreatOnePaper"),
    trueSimulation(BizKeyEnum.trueSimulation.getbiz(), "buildTrueSimulationPaper"),
    previosExam(BizKeyEnum.previosExam.getbiz(), "buildPreviousExamPaper"),
    centerPaper(BizKeyEnum.centerPaper.getbiz(), "buildCenterPaper"),
    centerNot(BizKeyEnum.centerNot.getbiz(), "buildCenterNotPaper"),
    centerError(BizKeyEnum.centerError.getbiz(), "buildCenterErrorPaper"),
    centerCollection(BizKeyEnum.centerCollection.getbiz(), "buildCenterCollectionPaper"),
    simPaper(BizKeyEnum.simPaper.getbiz(), "buildSimPaper"),
    intelligentPaper(BizKeyEnum.intelligentPaper.getbiz(), "buildSimPaper"),
    bigDatePaper(BizKeyEnum.bigDatePaper.getbiz(), "buildBigDatePaper"),
    selfHelpTest(BizKeyEnum.selfHelpTest.getbiz(), "buildSelfHelpTestPaper"),
    pointTest(BizKeyEnum.pointTest.getbiz(), "buildPointTestPaper"),
    unitTest(BizKeyEnum.unitTest.getbiz(), "buildUnitTestPaper"),
    frequencyPoint(BizKeyEnum.frequencyPoint.getbiz(), "buildFrequencyPointPaper"),
    weakPoint(BizKeyEnum.weakPoint.getbiz(), "buildWeakPointPaper"),
    questionPaper(BizKeyEnum.questionPaper.getbiz(), "buildQuestionPaper"),
    memoryCurve(BizKeyEnum.memoryCurve.getbiz(), "buildMemoryCurvePaper"),
    activitySimPaper(BizKeyEnum.activitySimPaper.getbiz(), "buildActivitySimPaperPaper");

    private final String simpleBizKey;
    private final String beanName;

    private BuildPaperEnum(String simpleBizKey, String beanName) {
        this.simpleBizKey = simpleBizKey;
        this.beanName = beanName;
    }

    public String getSimpleBizKey() {
        return this.simpleBizKey;
    }

    public String getBeanName() {
        return this.beanName;
    }

    public static String getBeanNameBySimpleBizKey(String simpleBizKey) {
        if (simpleBizKey != null && !simpleBizKey.trim().equals("")) {
            BuildPaperEnum[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                BuildPaperEnum buildPaperEnum = var1[var3];
                if (buildPaperEnum.getSimpleBizKey().equals(simpleBizKey)) {
                    return buildPaperEnum.getBeanName();
                }
            }

            return null;
        } else {
            return null;
        }
    }
}
