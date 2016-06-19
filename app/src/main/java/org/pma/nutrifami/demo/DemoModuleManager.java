package org.pma.nutrifami.demo;

import org.pma.nutrifami.lib.ModuleManager;
import org.pma.nutrifami.model.Lesson;
import org.pma.nutrifami.model.Module;
import org.pma.nutrifami.model.unit.IntroductionUnit;
import org.pma.nutrifami.model.unit.SwipeUnit;
import org.pma.nutrifami.model.unit.Unit;

/**
 * Created by juras on 19-Jun-16.
 */

public class DemoModuleManager extends ModuleManager {
    public DemoModuleManager() {
        this.setModules(new Module[]{new Module(
                "m1",
                "Introduction",
                "",
                new Lesson[]{
                        new Lesson(
                                "l1",
                                "Introduction",
                                "",
                                "HERE COMES THE INTRODUCTION TEXT",
                                new Unit[][]{
                                        new Unit[]{
                                                new IntroductionUnit(
                                                        "Introduction",
                                                        "This is the description, we have to figure out what to show here"
                                                )
                                        }, new Unit[]{
                                        new SwipeUnit(
                                                "There is a positive link between health and food",
                                                new String[]{"No", "Yes"},
                                                1,
                                                "Scientific studies all around the world showed that food and nutritional status have a strong influence on the health of people.",
                                                null
                                        ), new SwipeUnit(
                                        "Proteins, fats and carbohydrates are the parts of food that provide the energy which is necessary for a functioning human body.",
                                        new String[]{"No", "Yes"},
                                        1,
                                        "Different food provides us with different amount of carbohydrates, proteins and fats. It is important to consume different kinds of food to have enough of each nutrient  (carbohydrates, proteins and fats are types of nutrients).",
                                        null
                                ), new SwipeUnit(
                                        "Proteins are found mainly in flour, oats, barley, corn, bananas and root vegetables like potatoes and yuca.",
                                        new String[]{"No", "Yes"},
                                        0,
                                        "Scientific studies all around the world showed that food and nutritional status have a strong influence on the health of people.",
                                        null
                                ), new SwipeUnit(
                                        "Carbohydrates are found mainly in vegetable oil, butter, margarine, milk, cheese, peanuts and avocados.",
                                        new String[]{"No", "Yes"},
                                        0,
                                        "Carbohydrates are found mainly in flour, oats, barley, corn, bananas and root vegetables like potatoes and yuca.",
                                        null
                                ), new SwipeUnit(
                                        "I should eat one portion of fruit or vegetables per day.",
                                        new String[]{"No", "Yes"},
                                        0,
                                        "I should consume at least 5 servings of fruits and vegetables per day.",
                                        null
                                ), new SwipeUnit(
                                        "Consuming beverages is important to maintain health.",
                                        new String[]{"No", "Yes"},
                                        1,
                                        "I should drink water or other beverages daily and eat food like fruits, vegetables, soups and stews which also provide liquids. However, sugary beverages are not included in this category of maintaining health.",
                                        null
                                ), new SwipeUnit(
                                        "I should eat lots of fatty food (such as cakes and mayonnaise).",
                                        new String[]{"No", "Yes"},
                                        0,
                                        "You should eat fats only in moderate amounts. They should not make up the majority of your diet. Some fats are also better for you than others, for example those coming from animals or from sweets, and thus, should be eaten less. Good fats are found mainly in food such as vegetable oil, butter, margarine, milk, cheese, peanuts and avocado.",
                                        null
                                ), new SwipeUnit(
                                        "It is important to eat together with the family.",
                                        new String[]{"No", "Yes"},
                                        1,
                                        "Eating with the family is ideal to form proper eating habits. This way one can show children how to eat healthy.",
                                        null
                                )
                                }
                                }
                        )
                },
                true
        ), new Module(
                "m2",
                "Advanced",
                "",
                new Lesson[]{
                        new Lesson(
                                "l-notimpl",
                                "Some title",
                                "",
                                "",
                                new Unit[][]{
                                        new Unit[]{
                                                new IntroductionUnit("", "")
                                        }, new Unit[]{
                                        new SwipeUnit("", null, 0, "", "")
                                }
                                }
                        )
                },
                false
        )
        });
    }
}
