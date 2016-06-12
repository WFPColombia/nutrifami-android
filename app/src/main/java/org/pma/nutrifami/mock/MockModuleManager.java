package org.pma.nutrifami.mock;

import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Module;
import org.pma.nutrifami.model.unit.IntroductionUnit;
import org.pma.nutrifami.model.unit.PairUnit;
import org.pma.nutrifami.model.unit.SwipeUnit;
import org.pma.nutrifami.model.unit.TextQuizUnit;
import org.pma.nutrifami.model.unit.Unit;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class MockModuleManager extends ModuleManager {
    public MockModuleManager() {
        Lesson tq1 = new Lesson(
                "text-quiz-1",
                "Food basics",
                "NOT_IMPLEMENTED",
                "Look at the kind of long description that we have here. This lesson is just about the food basics though",
                new Unit[][]{new Unit[]{
                        new IntroductionUnit("Food basics", "Food basics are an important topic because basics are important and food is kinda as well. You could probably already guess the importance by looking at this wall of text. Don't be surprised but it's going to be quite boring to read further from this point onwards. Food basics are an important topic because basics are important and food is kinda as well. You could probably already guess the importance by looking at this wall of text. Don't be surprised but it's going to be quite boring to read further from this point onwards. Food basics are an important topic because basics are important and food is kinda as well. You could probably already guess the importance by looking at this wall of text. Don't be surprised but it's going to be quite boring to read further from this point onwards.")
                }, new Unit[]{

                        new TextQuizUnit(
                                "Which food is not a fruit?",
                                new String[]{"Apple", "Pork liver", "Banana", "Watermelon"},
                                1,
                                "Pork liver is meat",
                                "NOT_IMPLEMENTED"
                        ),
                        new TextQuizUnit(
                                "Which food is not a fruit?",
                                new String[]{"Apple", "Banana", "Watermelon", "Pork liver"},
                                3,
                                "It's still pork liver!",
                                "NOT_IMPLEMENTED"
                        ),
                        new TextQuizUnit(
                                "This is actually a fairly long question. I wouldn't have expected it myself, but look at this. The answer is Quarries btw",
                                new String[]{"Quarries", "An answer which is way too long but times are tough and stuff", "Y", "Watermelon"},
                                1,
                                "This is a fairly long explanation of why Quarries is the right answer for this rather long question. You might have been right or wrong but you're still reading this.",
                                "NOT_IMPLEMENTED"
                        )}, new Unit[]{
                        new SwipeUnit(
                                "Bananas are fruits right? This is some more text that is quite irrelevant but should still look decent.",
                                new String[]{"A long way to say no", "A long way to say yes"},
                                1,
                                "Bananas are indeed fruit!",
                                "NOT_IMPLEMENTED"
                        ),
                        new SwipeUnit(
                                "Bread is a fruit right? This is some more text that is quite irrelevant but should still look decent.",
                                new String[]{"A long way to say no", "A long way to say yes"},
                                0,
                                "Bread is not a fruit!",
                                "NOT_IMPLEMENTED"
                        ),
                        new SwipeUnit(
                                "Apples are fruits right? This is some more text that is quite irrelevant but should still look decent.",
                                new String[]{"A long way to say no", "A long way to say yes"},
                                1,
                                "Apples are fruits!",
                                "NOT_IMPLEMENTED"
                        )
                }, new Unit[]{
                        new PairUnit(
                                new String[][]{new String[]{"Football for life1", "Football for life2"},
                                        new String[]{"Food is important1", "Food is important2"},
                                        new String[]{"What you didn't know this?", "What you did though"}
                                }, "This is an explanation for the unit.",
                                "NOT_IMPLEMENTED"
                        ),
                        new PairUnit(
                                new String[][]{new String[]{"Football for life1", "Football for life2"},
                                        new String[]{"Food is important1", "Food is important2"},
                                        new String[]{"What you didn't know this?", "What you did though"}
                                }, "This is an explanation for the unit.",
                                "NOT_IMPLEMENTED"
                        ),
                        new PairUnit(
                                new String[][]{new String[]{"Football for life1", "Football for life2"},
                                        new String[]{"Food is important1", "Food is important2"},
                                        new String[]{"What you didn't know this?", "What you did though"}
                                }, "This is an explanation for the unit.",
                                "NOT_IMPLEMENTED"
                        ),
                        new PairUnit(
                                new String[][]{new String[]{"Football for life1", "Football for life2"},
                                        new String[]{"Food is important1", "Food is important2"},
                                        new String[]{"What you didn't know this?", "What you did though"}
                                }, "This is an explanation for the unit.",
                                "NOT_IMPLEMENTED"
                        ),
                }});

        this.setModules(new Module[]{
                new Module(
                        "module-1",
                        "The first module with a quite long title!",
                        "NOT_IMPLEMENTED",
                        new Lesson[]{tq1, tq1, tq1, tq1, tq1},
                        true
                ), new Module(
                "module-1",
                "The second module with a quite long title!",
                "NOT_IMPLEMENTED",
                new Lesson[]{tq1, tq1, tq1, tq1, tq1},
                false
        ), new Module(
                "module-1",
                "The third module with a quite long title!",
                "NOT_IMPLEMENTED",
                new Lesson[]{tq1, tq1, tq1, tq1, tq1},
                false
        ), new Module(
                "module-1",
                "The fourth module with a quite long title!",
                "NOT_IMPLEMENTED",
                new Lesson[]{tq1, tq1, tq1, tq1, tq1},
                false
        )});
    }
}
