package org.example.stages;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.Client;
import org.example.exceptions.ServerUnavailableException;
import org.example.models.StudyGroup;

import java.io.IOException;
import java.util.*;

public class AnimationStage {
    public Map<Long, StudyGroup> groupResponse = new LinkedHashMap<>();

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Pane animationPane;

    private Random random = new Random();
    private List<StickmanWrapper> stickmen = new ArrayList<>();
    private Timeline timeline;
    String[] userAdminNames;
    private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.PURPLE}; // Цвета для изменения

    @FXML
    public void initialize() {
        // Вызов метода для получения данных и запуска анимации после их получения
        fullCollectionMap();
    }

    public void fullCollectionMap() {
        try {
            Client.clientRequester.sendRequest("show", null, null, null);
        } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
            System.err.println("Error processing sending command show in AnimationStage " + e.getMessage());
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            try {
                groupResponse = (Map<Long, StudyGroup>) Client.clientHandler.receiveResponse();
                userAdminNames = groupResponse.values().stream()
                        .map(group -> group.getGroupId() + ": " + group.getUserName() + ": " + group.getAdminName())
                        .toArray(String[]::new);
                // Инициализация анимации после получения данных
                initializeAnimation();
            } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
                System.err.println("Receiving response from show command in AnimationStage " + e.getMessage());
            }
        });
        pause.play();
    }

    private void initializeAnimation() {
        int numberOfStickmen = userAdminNames.length;
        for (int i = 0; i < numberOfStickmen; i++) {
            Canvas canvas = new Canvas(100, 220);  // Canvas для каждого стикмена
            double dx = random.nextDouble() * 4 - 2; // Скорость по X от -2 до 2
            double dy = random.nextDouble() * 4 - 2; // Скорость по Y от -2 до 2
            StickmanWrapper wrapper = new StickmanWrapper(canvas, dx, dy, Long.parseLong(userAdminNames[i].split(": ")[0]), userAdminNames[i].split(": ")[1]);
            resetStickmanPosition(wrapper);
            stickmen.add(wrapper);
            animationPane.getChildren().add(canvas);
            drawStickman(canvas, userAdminNames[i]);

            // Добавляем обработчик события нажатия
            canvas.setOnMouseClicked(event -> handleStickmanClick(wrapper));
        }

        // Настройте анимацию
        timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            for (StickmanWrapper wrapper : stickmen) {
                moveStickman(wrapper);
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    @FXML
    public void backToCommands() throws IOException {
        Client.setRoot("commands");
    }

    @FXML
    private void handleStart() {
        timeline.play();
    }

    @FXML
    private void handleStop() {
        timeline.stop();
    }

    @FXML
    private void handleRestart() {
        timeline.stop();
        for (StickmanWrapper wrapper : stickmen) {
            resetStickmanPosition(wrapper);
            double dx = random.nextDouble() * 4 - 2; // Скорость по X от -2 до 2
            double dy = random.nextDouble() * 4 - 2; // Скорость по Y от -2 до 2
            wrapper.setDx(dx);
            wrapper.setDy(dy);
        }
        // timeline.play();
    }

    private void moveStickman(StickmanWrapper wrapper) {
        Canvas canvas = wrapper.getCanvas();
        double x = canvas.getLayoutX() + wrapper.getDx();
        double y = canvas.getLayoutY() + wrapper.getDy();

        // Проверьте границы и измените направление при необходимости
        if (x < 0 || x > animationPane.getWidth() - canvas.getWidth()) {
            wrapper.setDx(-wrapper.getDx());
            x = canvas.getLayoutX() + wrapper.getDx();
        }
        if (y < 0 || y > animationPane.getHeight() - canvas.getHeight()) {
            wrapper.setDy(-wrapper.getDy());
            y = canvas.getLayoutY() + wrapper.getDy();
        }

        // Обновите позиции
        canvas.setLayoutX(x);
        canvas.setLayoutY(y);
    }

    private void resetStickmanPosition(StickmanWrapper wrapper) {
        Canvas canvas = wrapper.getCanvas();
        canvas.setLayoutX(250);
        canvas.setLayoutY(270);
    }

    private void drawStickman(Canvas canvas, String userAdminNames) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);

        // Рисуем голову
        double headCenterX = canvas.getWidth() / 2;
        double headCenterY = 50;
        double headRadius = 20;
        gc.strokeOval(headCenterX - headRadius, headCenterY - headRadius, headRadius * 2, headRadius * 2);

        // Рисуем тело
        double bodyStartX = headCenterX;
        double bodyStartY = headCenterY + headRadius;
        double bodyEndY = bodyStartY + 60;
        gc.strokeLine(bodyStartX, bodyStartY, bodyStartX, bodyEndY);

        // Рисуем руки
        double armLength = 40;
        double armY = bodyStartY + 20;
        gc.strokeLine(bodyStartX, armY, bodyStartX - armLength, armY);
        gc.strokeLine(bodyStartX, armY, bodyStartX + armLength, armY);

        // Рисуем ноги
        double legLength = 50;
        double legY = bodyEndY;
        gc.strokeLine(bodyStartX, legY, bodyStartX - legLength, legY + legLength);
        gc.strokeLine(bodyStartX, legY, bodyStartX + legLength, legY + legLength);

        // Рисуем глаза
        double eyeRadius = 3;
        double eyeOffsetX = 8;
        double eyeOffsetY = 10;
        gc.setFill(Color.WHITE);
        gc.strokeOval(headCenterX - eyeOffsetX, headCenterY - eyeOffsetY, eyeRadius * 2, eyeRadius * 2);
        gc.strokeOval(headCenterX + eyeOffsetX - eyeRadius * 2, headCenterY - eyeOffsetY, eyeRadius * 2, eyeRadius * 2);
        gc.fillOval(headCenterX - eyeOffsetX, headCenterY - eyeOffsetY, eyeRadius * 2, eyeRadius * 2);
        gc.fillOval(headCenterX + eyeOffsetX - eyeRadius * 2, headCenterY - eyeOffsetY, eyeRadius * 2, eyeRadius * 2);

        // Рисуем волосы (заливка цветом)
        gc.setFill(Color.WHITE); // Цвет волос
        double hairStartY = headCenterY - headRadius;

        // Центральный треугольник
        double[] centerHairX = {headCenterX, headCenterX - 10, headCenterX + 10};
        double[] centerHairY = {hairStartY, hairStartY - 15, hairStartY - 15};
        gc.strokePolygon(centerHairX, centerHairY, 3);
        gc.fillPolygon(centerHairX, centerHairY, 3);

        // Левый треугольник
        double[] leftHairX = {headCenterX - 15, headCenterX - 20, headCenterX - 10};
        double[] leftHairY = {hairStartY + 5, hairStartY - 10, hairStartY - 10};
        gc.strokePolygon(leftHairX, leftHairY, 3);
        gc.fillPolygon(leftHairX, leftHairY, 3);

        // Правый треугольник
        double[] rightHairX = {headCenterX + 15, headCenterX + 20, headCenterX + 10};
        double[] rightHairY = {hairStartY + 5, hairStartY - 10, hairStartY - 10};
        gc.strokePolygon(rightHairX, rightHairY, 3);
        gc.fillPolygon(rightHairX, rightHairY, 3);

        // Рисуем имя над головой
        // Создаём текстовый объект для вычисления ширины текста
        Text text = new Text(userAdminNames);
        text.setFont(Font.font("Times New Roman", FontWeight.LIGHT, FontPosture.REGULAR, 15));
        double textWidth = text.getBoundsInLocal().getWidth();

// Вычисляем начальную точку для центрирования текста
        double textX = headCenterX - textWidth / 2;
        double textY = headCenterY - headRadius - 20;

//        gc.setFill(Color.WHITE);
//        gc.setStroke(Color.BLACK);
        gc.setFont(Font.font("Times New Roman", FontWeight.LIGHT, FontPosture.REGULAR, 15)); // Устанавливаем тонкий шрифт Times New Roman, размер 15, обычный
//        gc.setEffect(null); // Убираем все эффекты, если были
        gc.strokeText(userAdminNames, textX, textY); // Рисуем текст над головой, центрируя его
}

    private void handleStickmanClick(StickmanWrapper wrapper) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/fillCollectionObject.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(rootPane.getScene().getWindow());

            FillCollectionObjectDialogStage controller = loader.getController();
            controller.setAnimationStage(this, wrapper.getGroupId(), wrapper.getUsername());

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
