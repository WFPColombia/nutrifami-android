package org.pma.nutrifami.mock;

import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Module;
import org.pma.nutrifami.model.SwipeUnit;
import org.pma.nutrifami.model.TextQuizUnit;
import org.pma.nutrifami.model.Unit;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class MockModuleManager extends ModuleManager {
    public MockModuleManager() {
        // Text Quiz Lesson 1
        Lesson tq1 = new Lesson(
                "text-quiz-1",
                "Food basics",
                "NOT_IMPLEMENTED",
                "Look at the kind of long description that we have here. This lesson is just about the food basics though",
                new Unit[]{
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
                        )});

        Lesson tq2 = new Lesson(
                "text-quiz-2",
                "Not food basics",
                "NOT_IMPLEMENTED",
                "Look at the kind of long description that we have here. This lesson is just about the food basics though",
                new Unit[]{
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
                        )});

        Lesson tq3 = new Lesson(
                "text-quiz-3",
                "Not food basics",
                "NOT_IMPLEMENTED",
                "Look at the kind of long description that we have here. This lesson is just about the food basics though",
                new Unit[]{
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
                        )});

        Lesson tq4 = new Lesson(
                "text-quiz-4",
                "Not food basics",
                "NOT_IMPLEMENTED",
                "Look at the kind of long description that we have here. This lesson is just about the food basics though",
                new Unit[]{
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
                        )});

        Lesson tq5 = new Lesson(
                "swipe-1",
                "Swipe game",
                "NOT_IMPLEMENTED",
                "Swipe gae. Look at the kind of long description that we have here. This lesson is just about the food basics though",
                new Unit[]{
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
                                "Bananas are indeed fruit!",
                                "NOT_IMPLEMENTED"
                        ),
                        new SwipeUnit(
                                "Apples are fruits right? This is some more text that is quite irrelevant but should still look decent.",
                                new String[]{"A long way to say no", "A long way to say yes"},
                                1,
                                "Bananas are indeed fruit!",
                                "NOT_IMPLEMENTED"
                        )});

        Module firstModule = new Module(
                "module-1",
                "The first module with a quite long title!",
                "NOT_IMPLEMENTED",
                new String[] { "text-quiz-1" },
                new Lesson[] { tq5, tq2, tq3, tq4, tq1 }
        );

        this.setModules(new Module[] { firstModule });
    }
}
