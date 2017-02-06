#Transition

1. 普通过渡 Transition；
2. Shared Elements Transition 共享元素；
3. TransitionManager 控制动画；
4. ViewAnimationUtils 显示或隐藏效果。

#ViewOutlineProvider 的基本使用

        ImageView iv = (ImageView) findViewById(R.id.iv);
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int size = getResources().getDimensionPixelSize(R.dimen.shape_size);
                 <!-- outline.setRect(0,0,size,size); // 矩形 -->
                <!-- outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),size); // 圆角矩形 -->
                outline.setOval(0,0,size,size); // 圆形
            }
        };

        iv.setOutlineProvider(viewOutlineProvider);
        iv.setClipToOutline(true);

#ActivityTransition提供了两种Transition类型：

    Enter（进入）：进入一个Activity的效果
    Exit（退出）：退出一个Activity的效果
    而这每种类型又分为普通Transition和共享元素Transition：
###普通Transition：
    explode：从场景的中心移入或移出
    slide：从场景的边缘移入或移出
    fade：调整透明度产生渐变效果
###Shared Elements Transition 共享元素转换：
    它的作用就是共享两个acitivity种共同的元素，在Android 5.0下支持如下效果：
    changeBounds -  改变目标视图的布局边界
    changeClipBounds - 裁剪目标视图边界
    changeTransform - 改变目标视图的缩放比例和旋转角度
    changeImageTransform - 改变目标图片的大小和缩放比例

