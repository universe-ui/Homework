package com.example.test.ui.notifications;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private ImageButton Honors;
    private ImageButton Dribbling;
    private ImageButton Signed_sneakers;
    private FragmentNotificationsBinding binding;

    private ImageView ball, hoop;
    private TextView scoreText, shotsText;
    private Button restartButton;

    private int score = 0;
    private int shots = 0;

    private float ballStartX, ballStartY;
    private float touchStartX, touchStartY;

    private boolean isShooting = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Honors = binding.Honors;
        Dribbling = binding.Dribbling;
        Signed_sneakers = binding.SignedSneakers;
        Honors.setOnClickListener(v -> model(1,requireContext()));
        Dribbling.setOnClickListener(v -> model(2,requireContext()));
        Signed_sneakers.setOnClickListener(v -> model(3,requireContext()));

        ball = binding.ball;
        hoop = binding.hoop;
        scoreText = binding.scoreText;
        shotsText = binding.shotsText;
        restartButton = binding.restartButton;
        ball.post(new Runnable() {
            @Override
            public void run() {
                ballStartX = ball.getX();
                ballStartY = ball.getY();
            }
        });

        ball.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isShooting) return false;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchStartX = event.getRawX();
                        touchStartY = event.getRawY();
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        float deltaX = event.getRawX() - touchStartX;
                        float deltaY = event.getRawY() - touchStartY;
                        float newX = ball.getX() + deltaX;
                        float newY = ball.getY() + deltaY;
                        if (newX > 0 && newX < getView().getWidth() - ball.getWidth()) {
                            ball.setX(newX);
                        }
                        if (newY > 0 && newY < getView().getHeight() - ball.getHeight()) {
                            ball.setY(newY);
                        }

                        touchStartX = event.getRawX();
                        touchStartY = event.getRawY();
                        return true;

                    case MotionEvent.ACTION_UP:
                        shootBall();
                        return true;
                }
                return false;
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
        return root;
    }

    private void shootBall() {
        isShooting = true;
        shots++;
        shotsText.setText("投篮: " + shots);

        final float hoopCenterX = hoop.getX() + hoop.getWidth() / 2;
        final float hoopCenterY = hoop.getY() + hoop.getHeight() / 2;

        final float ballCenterX = ball.getX() + ball.getWidth() / 2;
        final float ballCenterY = ball.getY() + ball.getHeight() / 2;

        final float deltaX = hoopCenterX - ballCenterX;
        final float deltaY = hoopCenterY - ballCenterY;

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();

                float x = ballCenterX + fraction * deltaX;
                float y = ballCenterY + fraction * fraction * deltaY * 1.5f;

                ball.setX(x - ball.getWidth() / 2);
                ball.setY(y - ball.getHeight() / 2);
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                checkScore();

                resetBall();
                isShooting = false;
            }
        });

        animator.start();
    }

    private void checkScore() {
        float ballCenterX = ball.getX() + ball.getWidth() / 2;
        float ballCenterY = ball.getY() + ball.getHeight() / 2;

        float hoopLeft = hoop.getX() + 20;
        float hoopRight = hoop.getX() + hoop.getWidth() - 20;
        float hoopTop = hoop.getY() + 20;
        float hoopBottom = hoop.getY() + hoop.getHeight() - 20;

        if (ballCenterX > hoopLeft && ballCenterX < hoopRight &&
                ballCenterY > hoopTop && ballCenterY < hoopBottom) {
            score += 2;
            scoreText.setText("得分: " + score);
        }
    }

    private void resetBall() {
        ball.animate()
                .x(ballStartX)
                .y(ballStartY)
                .setDuration(500)
                .start();
    }

    private void resetGame() {
        score = 0;
        shots = 0;
        scoreText.setText("得分: " + score);
        shotsText.setText("投篮: " + shots);
        resetBall();
    }

    private void model(int key, Context context){
        Intent intent = new Intent(context, Model.class);
        intent.putExtra("key",key);
        startActivity(intent);
    }


    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}