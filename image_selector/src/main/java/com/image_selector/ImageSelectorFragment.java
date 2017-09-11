//package com.image_selector;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Intent;
//import android.content.res.Configuration;
//import android.database.Cursor;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.provider.MediaStore;
//import android.support.annotation.Nullable;
//import android.support.v4.app.LoaderManager;
//import android.support.v4.content.CursorLoader;
//import android.support.v4.content.Loader;
//import android.support.v7.widget.ListPopupWindow;
//import android.view.View;
//import android.view.ViewTreeObserver;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.GridView;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//import butterknife.BindView;
//import com.mingdao.R;
//import com.mingdao.app.adapters.ImageFolderAdapter;
//import com.mingdao.app.adapters.ImageGridAdapter;
//import com.mingdao.app.entity.ImageFile;
//import com.mingdao.app.entity.ImageFolder;
//import com.mingdao.presentation.eventbus.MDEventBus;
//import com.mingdao.presentation.ui.base.BaseFragmentV2;
//import com.mingdao.presentation.ui.base.IPresenter;
//import com.mingdao.presentation.ui.image.event.ImageSelectResultEvent;
//import com.mingdao.presentation.ui.preview.event.EventImagePreviewSelectRequest;
//import com.mingdao.presentation.ui.preview.event.EventImagePreviewSelection;
//import com.mingdao.presentation.ui.preview.model.PreviewImage;
//import com.mingdao.data.util.rx.SimpleSubscriber;
//import com.mylibs.utils.DisplayUtil;
//import com.mylibs.utils.FileUtil;
//import com.tbruyelle.rxpermissions.Permission;
//import in.workarounds.bundler.Bundler;
//import in.workarounds.bundler.annotations.Arg;
//import in.workarounds.bundler.annotations.RequireBundler;
//import in.workarounds.bundler.annotations.Required;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import org.greenrobot.eventbus.Subscribe;
//
///**
// * Edited by Jaeger on 16/6/29.
// *
// * Email: chjie.jaeger@gmail.com
// * GitHub: https://github.com/laobie
// */
//@RequireBundler
//public class ImageSelectorFragment extends BaseFragmentV2 {
//
//    private final static float IMAGE_FOLDER_ITEM_HEIGHT = 92f;
//    // 不同loader定义
//    private static final int LOADER_ALL = 0;
//    private static final int LOADER_CATEGORY = 1;
//    // 请求加载系统照相机
//    private static final int REQUEST_CAMERA = 100;
//    @Arg boolean isSingleSelect;
//    @Arg int mMaxSelectCount;
//    @Arg boolean isShowCamera;
//    @Arg @Required(false) ArrayList<ImageFile> mAlreadySelectedPicList;
//    @BindView(R.id.grid) GridView mGridView;
//    @BindView(R.id.category_btn) TextView mCategoryText;
//    @BindView(R.id.preview) Button mPreviewBtn;
//    @BindView(R.id.footer) RelativeLayout mPopupAnchorView;
//    @BindView(R.id.view_shadow_bg) View mViewShadowBg;
//    private ListPopupWindow mFolderPopupWindow;
//    private ImageGridAdapter mImageAdapter;
//    private ImageFolderAdapter mFolderAdapter;
//    private Callback mCallback;
//    // 结果数据
//    private ArrayList<ImageFile> resultList = new ArrayList<>();
//    // 文件夹数据
//    private ArrayList<ImageFolder> mResultImageFolder = new ArrayList<>();
//
//    private boolean hasFolderGened = false;
//
//    private int mGridViewWidth;
//    private int mGridViewHeight;
//
//    private File mTmpFile;
//    /**
//     * Loader 读取系统图片的回调
//     */
//    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {
//
//        private final String[] IMAGE_PROJECTION = {
//            MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATE_MODIFIED,
//            MediaStore.Images.Media.SIZE, MediaStore.Images.Media._ID
//        };
//
//        @Override
//        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//            if (id == LOADER_ALL) {
//                return new CursorLoader(getActivity(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION, null, null,
//                    IMAGE_PROJECTION[2] + " DESC");
//            } else if (id == LOADER_CATEGORY) {
//                return new CursorLoader(getActivity(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
//                    IMAGE_PROJECTION[0] + " like '%" + args.getString("path") + "%'", null, IMAGE_PROJECTION[2] + " DESC");
//            }
//
//            return null;
//        }
//
//        @Override
//        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//            if (data == null || data.getCount() <= 0) {
//                return;
//            }
//            // 读取图片数据
//            List<ImageFile> images = new ArrayList<>();
//            data.moveToFirst();
//            do {
//                String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
//                String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
//                long dateTime = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
//                long fileSize = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[3]));
//                File file = new File(path);
//                if (file.exists()) {
//                    ImageFile image = new ImageFile(path, name, dateTime, fileSize);
//                    images.add(image);
//                    if (!hasFolderGened) {
//                        // 获取文件夹名称
//                        File imageFile = new File(path);
//                        File folderFile = imageFile.getParentFile();
//                        ImageFolder imageFolder = new ImageFolder();
//                        imageFolder.name = folderFile.getName();
//                        imageFolder.path = folderFile.getAbsolutePath();
//                        imageFolder.cover = image;
//                        if (!mResultImageFolder.contains(imageFolder)) {
//                            List<ImageFile> imageList = new ArrayList<>();
//                            imageList.add(image);
//                            imageFolder.images = imageList;
//                            mResultImageFolder.add(imageFolder);
//                        } else {
//                            // 更新
//                            ImageFolder folder = mResultImageFolder.get(mResultImageFolder.indexOf(imageFolder));
//                            folder.images.add(image);
//                        }
//                    }
//                }
//            } while (data.moveToNext());
//
//            mImageAdapter.setData(images);
//            // 设定默认选择
//            if (resultList != null && resultList.size() > 0) {
//                mImageAdapter.setDefaultSelected(resultList);
//            }
//
//            mFolderAdapter.setData(mResultImageFolder);
//            hasFolderGened = true;
//        }
//
//        @Override
//        public void onLoaderReset(Loader<Cursor> loader) {
//
//        }
//    };
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mCallback = (Callback) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException("The Activity must implement MultiImageSelectorFragment.Callback interface...");
//        }
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_multi_image;
//    }
//
//    @Override
//    protected void initInjector() {
//        Bundler.inject(this);
//        MDEventBus.getBus().register(this);
//    }
//
//    @Override
//    protected IPresenter bindPresenter() {
//        return null;
//    }
//
//    @Override
//    public void onDestroy() {
//        MDEventBus.getBus().unregister(this);
//        super.onDestroy();
//    }
//
//    @Override
//    protected void setView() {
//        // 是否显示照相机
//        mImageAdapter = new ImageGridAdapter(getActivity(), isShowCamera);
//        // 是否显示选择指示器
//        mImageAdapter.showSelectIndicator(!isSingleSelect);
//        mCategoryText.setText(R.string.folder_all);
//        mCategoryText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mFolderPopupWindow.isShowing()) {
//                    mFolderPopupWindow.dismiss();
//                } else {
//                    setFolderPopWindowSize(mGridViewWidth, mGridViewHeight);
//                    mFolderPopupWindow.show();
//                    mViewShadowBg.setVisibility(View.VISIBLE);
//                    int index = mFolderAdapter.getSelectIndex();
//                    index = index == 0 ? index : index - 1;
//                    if (mFolderPopupWindow.getListView() != null) {
//                        mFolderPopupWindow.getListView().setSelection(index);
//                    }
//                }
//            }
//        });
//
//        setPreViewStatus();
//        if (isSingleSelect) {
//            mPreviewBtn.setVisibility(View.GONE);
//        } else {
//            mPreviewBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    gotoImageSelectedPreviewView();
//                }
//            });
//        }
//
//        mGridView.setAdapter(mImageAdapter);
//        monitorGridViewSizeChange();
//        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (mImageAdapter.isShowCamera() && i == 0) {
//                    requestPermission(Manifest.permission.CAMERA).subscribe(new SimpleSubscriber<Permission>() {
//                        @Override
//                        public void onNext(Permission permission) {
//                            if (permission.granted) {
//                                showCameraAction();
//                            } else {
//                                showMsg(R.string.please_grant_permission);
//                            }
//                        }
//                    });
//                } else if (isSingleSelect) {
//                    selectImageFromGrid(mImageAdapter.getItem(i));
//                } else {
//                    gotoImagePreviewView(mImageAdapter.isShowCamera() ? i - 1 : i);
//                }
//            }
//        });
//        mImageAdapter.setOnCheckClickListener(new ImageGridAdapter.OnCheckClickListener() {
//            @Override
//            public void onCheckClick(int position) {
//                ImageFile image = mImageAdapter.getItem(position);
//                selectImageFromGrid(image);
//            }
//        });
//        mFolderAdapter = new ImageFolderAdapter(getActivity());
//    }
//
//    private void gotoImagePreviewView(int index) {
//        ArrayList<PreviewImage> images = new ArrayList<>();
//        for (ImageFile imageFile : mImageAdapter.getData()) {
//            PreviewImage previewImage = PreviewImage.from(imageFile);
//            if (resultList.contains(imageFile)) {
//                previewImage.isSelected = true;
//                previewImage.isOriginFileSelected = resultList.get(resultList.indexOf(imageFile)).isOriginFile;
//            }
//            images.add(previewImage);
//        }
//        Bundler.imagePreviewWithOriginSelectionActivity(index, ImageSelectorFragment.class, null, mMaxSelectCount)
//            .start(getActivity());
//        MDEventBus.getBus().postSticky(new EventImagePreviewSelectRequest(images));
//    }
//
//    private void gotoImageSelectedPreviewView() {
//        ArrayList<PreviewImage> images = new ArrayList<>();
//        for (ImageFile imageFile : resultList) {
//            PreviewImage previewImage = PreviewImage.from(imageFile);
//            previewImage.isSelected = true;
//            images.add(previewImage);
//        }
//        Bundler.imagePreviewWithOriginSelectionActivity(0, ImageSelectorFragment.class, null, mMaxSelectCount)
//            .start(getActivity());
//        MDEventBus.getBus().postSticky(new EventImagePreviewSelectRequest(images));
//    }
//
//    @Override
//    public void initData() {
//        if (!isSingleSelect) {
//            ArrayList<ImageFile> tmp = mAlreadySelectedPicList;
//            if (tmp != null && tmp.size() > 0) {
//                resultList = tmp;
//            }
//        }
//    }
//
//    /**
//     * 设置相册文件夹 PopupWindow 的尺寸
//     *
//     * @param width             宽度
//     * @param contentViewHeight 高度
//     */
//    private void setFolderPopWindowSize(int width, int contentViewHeight) {
//        int maxHeight = contentViewHeight * 5 / 7;
//        int realHeight = mFolderAdapter.getCount() * DisplayUtil.dp2Px(getContext(), IMAGE_FOLDER_ITEM_HEIGHT);
//
//        int height = realHeight < maxHeight ? realHeight : maxHeight;
//        if (mFolderPopupWindow == null) {
//            createPopupFolderList(width, height);
//        } else {
//            mFolderPopupWindow.setWidth(width);
//            mFolderPopupWindow.setHeight(height);
//        }
//    }
//
//    /**
//     * 创建弹出的相册文件夹的 PopupWindow
//     *
//     * @param width  PopupWindow 宽度
//     * @param height PopupWindow 高度
//     */
//    private void createPopupFolderList(int width, int height) {
//        mFolderPopupWindow = new ListPopupWindow(getActivity());
//        mFolderPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        mFolderPopupWindow.setAdapter(mFolderAdapter);
//        mFolderPopupWindow.setContentWidth(width);
//        mFolderPopupWindow.setWidth(width);
//        mFolderPopupWindow.setHeight(height);
//        mFolderPopupWindow.setAnchorView(mPopupAnchorView);
//        mFolderPopupWindow.setModal(true);
//        mFolderPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                mViewShadowBg.setVisibility(View.GONE);
//            }
//        });
//        mFolderPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
//                requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new SimpleSubscriber<Permission>() {
//                    @Override
//                    public void onNext(Permission permission) {
//                        if (permission.granted) {
//                            mFolderAdapter.setSelectIndex(i);
//
//                            final int index = i;
//                            final AdapterView v = adapterView;
//
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mFolderPopupWindow.dismiss();
//                                    if (index == 0) {
//                                        getActivity().getSupportLoaderManager().restartLoader(LOADER_ALL, null, mLoaderCallback);
//                                        mCategoryText.setText(R.string.folder_all);
//                                        mImageAdapter.setShowCamera(isShowCamera);
//                                    } else {
//                                        ImageFolder imageFolder = (ImageFolder) v.getAdapter().getItem(index);
//                                        if (null != imageFolder) {
//                                            mImageAdapter.setData(imageFolder.images);
//                                            mCategoryText.setText(imageFolder.name);
//                                            // 设定默认选择
//                                            if (resultList != null && resultList.size() > 0) {
//                                                mImageAdapter.setDefaultSelected(resultList);
//                                            }
//                                        }
//                                        mImageAdapter.setShowCamera(false);
//                                    }
//                                    // 滑动到最初始位置
//                                    mGridView.smoothScrollToPosition(0);
//                                }
//                            }, 100);
//                        } else {
//                            showMsg(R.string.please_grant_permission);
//                        }
//                    }
//                });
//            }
//        });
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new SimpleSubscriber<Permission>() {
//            @Override
//            public void onNext(Permission permission) {
//                if (permission.granted) {
//                    // 首次加载所有图片
//                    getActivity().getSupportLoaderManager().initLoader(LOADER_ALL, null, mLoaderCallback);
//                } else {
//                    showMsg(R.string.please_grant_permission);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // 相机拍照完成后，返回图片路径
//        if (requestCode == REQUEST_CAMERA) {
//            if (resultCode == Activity.RESULT_OK) {
//                if (mTmpFile != null) {
//                    if (mCallback != null) {
//                        mCallback.onCameraShot(mTmpFile);
//                    }
//                }
//            } else {
//                if (mTmpFile != null && mTmpFile.exists()) {
//                    //noinspection ResultOfMethodCallIgnored
//                    mTmpFile.delete();
//                }
//            }
//        }
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        // 处理屏幕旋转问题
//        if (mFolderPopupWindow != null && mFolderPopupWindow.isShowing()) {
//            mFolderPopupWindow.dismiss();
//        }
//        monitorGridViewSizeChange();
//        super.onConfigurationChanged(newConfig);
//    }
//
//    @Subscribe
//    public void onEventImagePreviewSelection(EventImagePreviewSelection eventImagePreviewSelection) {
//        if (!eventImagePreviewSelection.check(ImageSelectorFragment.class, null)) {
//            return;
//        }
//        if (eventImagePreviewSelection.mImages == null) {
//            return;
//        }
//        resultList.clear();
//        for (PreviewImage previewImage : eventImagePreviewSelection.mImages) {
//            ImageFile imageFile = new ImageFile(previewImage.getFilePath(), previewImage.getName(), 0, previewImage.getSize(),
//                previewImage.isOriginFileSelected);
//            resultList.add(imageFile);
//        }
//        if (mCallback != null) {
//            mCallback.onImageSelectChanged(resultList);
//        }
//        mImageAdapter.setDefaultSelected(resultList);
//        setPreViewStatus();
//    }
//
//    @Subscribe
//    public void onImageSelectResultEvent(ImageSelectResultEvent imageSelectResultEvent) {
//        if (!imageSelectResultEvent.check(ImageSelectorFragment.class, null)) {
//            return;
//        }
//        if (mCallback != null) {
//            mCallback.onImageSelectFinish(imageSelectResultEvent.getSelectedImages());
//        }
//    }
//
//    /**
//     * 监控 GridView 的变化改变相册文件夹的尺寸
//     */
//    private void monitorGridViewSizeChange() {
//        mGridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                int width = mGridView.getWidth();
//                int height = mGridView.getHeight();
//                mGridViewWidth = width;
//                mGridViewHeight = height;
//                setFolderPopWindowSize(width, height);
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                } else {
//                    mGridView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                }
//            }
//        });
//    }
//
//    /**
//     * 选择相机
//     */
//    private void showCameraAction() {
//        // 跳转到系统照相机
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//            // 设置系统相机拍照后的输出路径
//            // 创建临时文件
//            mTmpFile = FileUtil.createCameraFile(getActivity());
//            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
//            startActivityForResult(cameraIntent, REQUEST_CAMERA);
//        } else {
//            Toast.makeText(getActivity(), R.string.msg_no_camera, Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    /**
//     * 选择图片操作
//     */
//    private void selectImageFromGrid(ImageFile image) {
//        if (image == null) {
//            return;
//        }
//        if (isSingleSelect) {
//            if (mCallback != null) {
//                image.isOriginFile = true;
//                mCallback.onSingleImageSelected(image);
//            }
//        } else {
//            if (resultList.contains(image)) {
//                resultList.remove(image);
//                if (mCallback != null) {
//                    mCallback.onImageUnselected(image);
//                }
//            } else {
//                // 判断选择数量问题
//                if (mMaxSelectCount == resultList.size()) {
//                    Toast.makeText(getActivity(), R.string.msg_amount_limit, Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                resultList.add(image);
//                if (mCallback != null) {
//                    mCallback.onImageSelected(image);
//                }
//            }
//            setPreViewStatus();
//            mImageAdapter.select(image);
//        }
//    }
//
//    /**
//     * 设置预 按钮的状态
//     *
//     * 注:暂时没这个功能 (16.04.07)
//     */
//    private void setPreViewStatus() {
//        if (resultList != null && resultList.size() != 0) {
//            mPreviewBtn.setEnabled(true);
//            mPreviewBtn.setText(util().res().getString(R.string.preview) + "(" + resultList.size() + ")");
//        } else {
//            mPreviewBtn.setEnabled(false);
//            mPreviewBtn.setText(R.string.preview);
//        }
//    }
//
//    /**
//     * 回调接口
//     */
//    public interface Callback {
//        void onSingleImageSelected(ImageFile imageFile);
//
//        void onImageSelected(ImageFile imageFile);
//
//        void onImageUnselected(ImageFile imageFile);
//
//        void onImageSelectChanged(ArrayList<ImageFile> imageFiles);
//
//        void onCameraShot(File file);
//
//        void onImageSelectFinish(List<ImageFile> imageFiles);
//    }
//}
