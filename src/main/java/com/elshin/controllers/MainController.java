package com.elshin.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private RadioButton radioButton1;
    @FXML
    private RadioButton radioButton2;
    @FXML
    private RadioButton radioButton3;
    @FXML
    private RadioButton radioButton4;
    @FXML
    private RadioButton radioButton1_2;
    @FXML
    private RadioButton radioButton2_2;
    @FXML
    private RadioButton radioButton3_2;
    @FXML
    private RadioButton radioButton4_2;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button buttonBegin;
    @FXML
    private Button buttonNext;
    @FXML
    private Label labelTextOfQuestion1;
    @FXML
    private Label labelTextOfQuestion2;
    @FXML
    private TextArea textOfQuestion;
    @FXML
    private TextArea textOfMethod;
    @FXML
    private Button buttonMethod;
    @FXML
    private Pane image;
    @FXML
    private ImageView imageView;
    int numOfQuestion = 0;
    int balls = 0;

    @FXML
    void initialize() {
        textOfMethod.setText(" Методика \"Средние суждения\" (Pettegrew, 1958)" + "\n\n\n Вам будут представлены задания следующего типа: сначала сообщается средняя \n величина какого-либо явления или объекта (средний рост женщины, средняя \n скорость молекулы и т.д.), затем вам будет предложено выбрать максимальную \n и минимальную возможную величину этого явления или объекта из четырех \n предложенных вариантов. \n\n\n После того как вы укажете ответы на все вопросы, каждый ответ будет \n оцениваться определенным количеством баллов. \n\n" +
                "Показателем меры является сумма баллов по всем вопросам.");
        buttonBegin.setOnAction(event -> {

            textOfMethod.setVisible(false);
            buttonBegin.setVisible(false);
            textOfQuestion.setVisible(true);
            buttonNext.setVisible(true);
            gridPane.setVisible(true);
            image.setVisible(true);
            numOfQuestion = 1;
            imageView.setVisible(true);

            imageView.setImage(new Image("images/1.jpg"));

            textOfQuestion.setText("Орнитологи установили, что средняя скорость полета птицы составляет 27 км/час. Какова, по вашему мнению:");
            initQuestionA("скорость полета самой быстрой птицы?", "40 км/ч", "169 км/ч", "118 км/ч", "55 км/ч");
            initQuestionB("скорость полета самой медленной птицы?", "16 км/ч", "3 км/ч", "19 км/ч ", "8 км/ч");
        });

        radioButton1.setSelected(true);
        radioButton1_2.setSelected(true);

        buttonNext.setOnAction(event -> {//обработка нажатия на кнопку "Следующее"
            numOfQuestion++;

            if (numOfQuestion == 2) {
                balls += getCountOfBalls1();
                textOfQuestion.setText("Считается, что средний рост человека в мире - 165 см. Какой, по вашему мнению:");
                initQuestionA("рост самого высокого человека?", "272 см", "254 см", "221 см", "198 см");
                initQuestionB("рост самого низкого человека?", "54 см", "60 см", "72 см ", "97 см");
                imageView.setImage(new Image("images/2.jpg"));
            } else if (numOfQuestion == 3) {
                balls += getCountOfBalls2();
                textOfQuestion.setText("Средняя глубина Арафурского моря - 186 м. Какова, по вашему мнению:");
                initQuestionA("наибольшая глубина?", "1132 м", "1893 м", "2984 м", "3680 м");
                initQuestionB("наименьшая глубина?", "54 м", "65 м", "32 м", "78 м");
                imageView.setImage(new Image("images/3.jpg"));
            } else if (numOfQuestion == 4) {
                balls += getCountOfBalls3();
                textOfQuestion.setText("Средняя величина кочанчиков Брюссельской капусты - 2.5 см. Какова, по вашему мнению:");
                initQuestionA("величина самых маленьких кочанчиков?", "1.9 см", "1.5 см", "1.3 см", "2.2 см");
                initQuestionB("величина самых крупных кочанчиков?", "2.3 см", "3.1 см", "3.9 см", "4.2 см");
                buttonNext.setText("Узнать результат");
                imageView.setImage(new Image("images/4.jpg"));
            } else {
                balls += getCountOfBalls4();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Результат теста!");
                alert.setHeaderText("Вы набрали " + balls + " баллов");
                if (balls >= 8 && balls <= 16)
                    alert.setContentText("Это узкий диапазон эквивалентности");
                else if (balls >= 17 && balls <= 32)
                    alert.setContentText("Это широкий диапазон эквивалентности");
                alert.showAndWait();

                Stage thisStage = (Stage) buttonNext.getScene().getWindow();//по кнопке ищем форму (для закрытия)
                thisStage.close();//закрываем форму
            }
        });
    }

    void initQuestionA(String question, String radioButton1Text, String radioButton2Text, String radioButton3Text, String radioButton4Text) {//для левого
        labelTextOfQuestion1.setText(question);
        radioButton1.setText(radioButton1Text);
        radioButton2.setText(radioButton2Text);
        radioButton3.setText(radioButton3Text);
        radioButton4.setText(radioButton4Text);
    }

    void initQuestionB(String question, String radioButton1Text, String radioButton2Text, String radioButton3Text, String radioButton4Text) {//для правого
        labelTextOfQuestion2.setText(question);
        radioButton1_2.setText(radioButton1Text);
        radioButton2_2.setText(radioButton2Text);
        radioButton3_2.setText(radioButton3Text);
        radioButton4_2.setText(radioButton4Text);
    }

    int getCountOfBalls1() {
        int sum = 0;

        if (radioButton1.isSelected())
            sum += 1;
        else if (radioButton2.isSelected())
            sum += 4;
        else if (radioButton3.isSelected())
            sum += 3;
        else if (radioButton4.isSelected())
            sum += 2;

        if (radioButton1_2.isSelected())
            sum += 2;
        else if (radioButton2_2.isSelected())
            sum += 4;
        else if (radioButton3_2.isSelected())
            sum += 1;
        else if (radioButton4_2.isSelected())
            sum += 3;

        return sum;
    }

    int getCountOfBalls2() {
        int sum = 0;

        if (radioButton1.isSelected())
            sum += 4;
        else if (radioButton2.isSelected())
            sum += 3;
        else if (radioButton3.isSelected())
            sum += 2;
        else if (radioButton4.isSelected())
            sum += 1;

        if (radioButton1_2.isSelected())
            sum += 4;
        else if (radioButton2_2.isSelected())
            sum += 3;
        else if (radioButton3_2.isSelected())
            sum += 2;
        else if (radioButton4_2.isSelected())
            sum += 1;

        return sum;
    }

    int getCountOfBalls3() {
        int sum = 0;

        if (radioButton1.isSelected())
            sum += 1;
        else if (radioButton2.isSelected())
            sum += 2;
        else if (radioButton3.isSelected())
            sum += 3;
        else if (radioButton4.isSelected())
            sum += 4;

        if (radioButton1_2.isSelected())
            sum += 2;
        else if (radioButton2_2.isSelected())
            sum += 3;
        else if (radioButton3_2.isSelected())
            sum += 1;
        else if (radioButton4_2.isSelected())
            sum += 4;

        return sum;
    }

    int getCountOfBalls4() {
        int sum = 0;

        if (radioButton1.isSelected())
            sum += 2;
        else if (radioButton2.isSelected())
            sum += 3;
        else if (radioButton3.isSelected())
            sum += 4;
        else if (radioButton4.isSelected())
            sum += 1;

        if (radioButton1_2.isSelected())
            sum += 1;
        else if (radioButton2_2.isSelected())
            sum += 2;
        else if (radioButton3_2.isSelected())
            sum += 3;
        else if (radioButton4_2.isSelected())
            sum += 4;

        return sum;
    }
}

