package org.pma.nutrifami.model;

import org.pma.nutrifami.R;

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
        this.mFirstExplanation = firstExplanation;
        this.mSecondExplanation = secondExplanation;
        this.mThirdExplanation = thirdExplanation;
        this.mFirstExplanationIcon = firstExplanationIcon;
        this.mSecondExplanationIcon = secondExplanationIcon;
        this.mThirdExplanationIcon = thirdExplanationIcon;
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
                "GAIN NUTRITION KNOWLEDGE",
                R.mipmap.books_with_apple_white,
                "EARN CREDITS FOR YOUR VOUCHER",
                R.mipmap.coins_banknotes_white,
                "BECOME HAPPY AND HEALTHY",
                R.mipmap.healthy_lifestyle_white
        );
    }
}
