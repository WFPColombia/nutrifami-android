package org.pma.nutrifami.model;

public class BenefitsExplanation extends Explanation {
    private String mFirstExplanation;
    private int mFirstExplanationIcon;
    private String mSecondExplanation;
    private int mSecondExplanationIcon;
    private String mThirdExplanation;
    private int mThirdExplanationIcon;

    public BenefitsExplanation(
            String title,
            String firstExplanation,
            int firstExplanationIcon,
            String secondExplanation,
            int secondExplanationIcon,
            String thirdExplanation,
            int thirdExplanationIcon
    ) {
        super(title);
    }

    public String getFirstExplanation() {
        return this.mFirstExplanation;
    }

    public int getFirstExplanationIcon() {
        return this.mFirstExplanationIcon;
    }

    public String getSecondExplanation() {
        return this.mSecondExplanation;
    }

    public int getSecondExplanationIcon() {
        return this.mSecondExplanationIcon;
    }

    public String getThirdExplanation() {
        return this.mThirdExplanation;
    }

    public int getThirdExplanationIcon() {
        return this.mThirdExplanationIcon;
    }

    public static BenefitsExplanation getBenefits() {
        return new BenefitsExplanation(
            "BENEFITS",
            "EARN CREDITS FOR YOUR VOUCHER",
            0,
            "GAIN NUTRITION KNOWLEDGE",
            0,
            "BECOME HAPPY AND HEALTHY",
            0
        );
    }
}
