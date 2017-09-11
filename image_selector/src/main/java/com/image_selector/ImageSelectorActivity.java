//package com.image_selector;
//
//import android.support.v4.app.Fragment;
//import android.view.Menu;
//import android.view.MenuItem;
//import com.mingdao.R;
//import com.mingdao.app.entity.ImageFile;
//import com.mingdao.presentation.eventbus.MDEventBus;
//import com.mingdao.presentation.ui.base.BaseActivityV2;
//import com.mingdao.presentation.ui.base.IPresenter;
//import com.mingdao.presentation.ui.image.event.ImageSelectResultEvent;
//import com.mingdao.presentation.ui.post.NewSendPostActivity;
//import com.mingdao.presentation.ui.post.PostActivity;
//import in.workarounds.bundler.Bundler;
//import in.workarounds.bundler.annotations.Arg;
//import in.workarounds.bundler.annotations.RequireBundler;
//import in.workarounds.bundler.annotations.Required;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Edited by Jaeger on 16/6/29.
// *
// * Email: chjie.jaeger@gmail.com
// * GitHub: https://github.com/laobie
// */
//@RequireBundler
//public class ImageSelectorActivity extends BaseActivityV2 implements ImageSelectorFragment.Callback {
//    public static final int DEFAULT_MAX_COUNT = 9;
//
//    @Arg boolean isSingleSelect;
//    @Arg int mMaxSelectCount = DEFAULT_MAX_COUNT;
//    @Arg boolean isShowCamera;
//    @Arg @Required(false) ArrayList<ImageFile> mSelectedPicPathList;
//    /*
//    是否是直接选择图片发送动态，目前只从动态首页跳转过来
//     */
//    @Arg @Required(false) boolean isSelectForPost = false;
//    /*
//    EventBus 检校使用
//     */
//    @Arg Class mClass;
//    @Arg String mId;
//
//    private ArrayList<ImageFile> mSelectedImageList = new ArrayList<>();
//    private MenuItem mFinishMenu;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_image_selector;
//    }
//
//    @Override
//    protected void initInjector() {
//        Bundler.inject(this);
//    }
//
//    @Override
//    protected IPresenter bindPresenter() {
//        return null;
//    }
//
//    @Override
//    protected void setView() {
//        setTitle(R.string.select_picture);
//        Fragment fragment = Bundler.imageSelectorFragment(isSingleSelect, mMaxSelectCount, isShowCamera)
//            .mAlreadySelectedPicList(mSelectedPicPathList)
//            .create();
//        getSupportFragmentManager().beginTransaction().add(R.id.image_grid, fragment).commit();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_image_select, menu);
//        mFinishMenu = menu.findItem(R.id.action_finish);
//        setSelectStatus();
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_finish:
//                postResultEvent();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void initData() {
//
//    }
//
//    @Override
//    public void onSingleImageSelected(ImageFile ImageFile) {
//        mSelectedImageList.add(ImageFile);
//        postResultEvent();
//    }
//
//    @Override
//    public void onImageSelected(ImageFile ImageFile) {
//        if (!mSelectedImageList.contains(ImageFile)) {
//            mSelectedImageList.add(ImageFile);
//        }
//        setSelectStatus();
//    }
//
//    @Override
//    public void onImageUnselected(ImageFile imageFile) {
//        if (mSelectedImageList.contains(imageFile)) {
//            mSelectedImageList.remove(imageFile);
//            setSelectStatus();
//        }
//    }
//
//    @Override
//    public void onImageSelectChanged(ArrayList<ImageFile> imageFiles) {
//        mSelectedImageList.clear();
//        mSelectedImageList.addAll(imageFiles);
//        setSelectStatus();
//    }
//
//    private void setSelectStatus() {
//        if (isSingleSelect) {
//            mFinishMenu.setVisible(false);
//        } else {
//            mFinishMenu.setVisible(true);
//            mFinishMenu.setEnabled(mSelectedImageList.size() > 0);
//        }
//    }
//
//    @Override
//    public void onCameraShot(File file) {
//        if (file != null) {
//            mSelectedImageList.add(new ImageFile(file.getAbsolutePath(), file.getName(), file.lastModified(), file.length()));
//            postResultEvent();
//        }
//    }
//
//    @Override
//    public void onImageSelectFinish(List<ImageFile> imageFiles) {
//        if (isSelectForPost) {
//            gotoSendPostPage(imageFiles);
//        } else {
//            MDEventBus.getBus().post(new ImageSelectResultEvent(imageFiles, mClass, mId));
//        }
//        finish();
//    }
//
//    private void gotoSendPostPage(List<ImageFile> imageFiles) {
//        Bundler.newSendPostActivity(NewSendPostActivity.SendType.SEND_POST, null, null, PostActivity.class)
//            .mSelectedImageFileList((ArrayList<ImageFile>) imageFiles)
//            .start(this);
//    }
//
//    private void postResultEvent() {
//        if (isSelectForPost) {
//            gotoSendPostPage(mSelectedImageList);
//        } else {
//            MDEventBus.getBus().post(new ImageSelectResultEvent(mSelectedImageList, mClass, mId));
//        }
//        finish();
//    }
//}
