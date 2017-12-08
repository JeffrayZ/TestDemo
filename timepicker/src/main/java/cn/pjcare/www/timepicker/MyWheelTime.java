package cn.pjcare.www.timepicker;

import android.util.Log;
import android.view.View;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.NumericWheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class MyWheelTime {
    private View view;
    private WheelView wv_year;
    private WheelView wv_month;
    private WheelView wv_day;
    private WheelView wv_option1;
    private String[] mOptions1Items = {"上午","下午"};
    private List<String> optionsList = new ArrayList<>();
    private int gravity;

    private boolean[] type;
    private static final int DEFAULT_START_YEAR = 1900;
    private static final int DEFAULT_END_YEAR = 2100;
    private static final int DEFAULT_START_MONTH = 1;
    private static final int DEFAULT_END_MONTH = 12;
    private static final int DEFAULT_START_DAY = 1;
    private static final int DEFAULT_END_DAY = 31;

    private int startYear = DEFAULT_START_YEAR;
    private int endYear = DEFAULT_END_YEAR;
    private int startMonth = DEFAULT_START_MONTH;
    private int endMonth = DEFAULT_END_MONTH;
    private int startDay = DEFAULT_START_DAY;
    private int endDay = DEFAULT_END_DAY; //表示31天的
    private int currentYear;


    // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
    private int textSize = 18;
    //文字的颜色和分割线的颜色
    int textColorOut;
    int textColorCenter;
    int dividerColor;
    // 条目间距倍数
    float lineSpacingMultiplier = 1.6F;

    private WheelView.DividerType dividerType;
    private String schedule; // 排班表

    public MyWheelTime(View view) {
        super();
        this.view = view;
        type = new boolean[]{true, true, true};
        setView(view);
    }

    public MyWheelTime(View view, boolean[] type, int gravity, int textSize) {
        super();
        this.view = view;
        this.type = type;
        this.gravity = gravity;
        this.textSize = textSize;
        setView(view);
    }

//    public void setPicker(int year, int month, int day,String schedule) {
//        this.setPicker(year, month, day, 0, 0, 0,schedule);
//    }


    public void setPicker(int year, int month, int day) {
        this.setPicker(year, month, day, 0, 0, 0);
    }

    public void setPicker(int year, final int month, int day, int h, int m, int s) {
        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);

      /*  final Context context = view.getContext();*/
        currentYear = year;
        // 年
        wv_year = (WheelView) view.findViewById(R.id.wl_year);
        wv_year.setAdapter(new NumericWheelAdapter(startYear, endYear));// 设置"年"的显示数据
        /*wv_year.setLabel(context.getString(R.string.pickerview_year));// 添加文字*/
        wv_year.setCurrentItem(year - startYear);// 初始化时显示的数据
        wv_year.setGravity(gravity);
        // 月
        wv_month = (WheelView) view.findViewById(R.id.wl_month);
        if (startYear == endYear) {//开始年等于终止年
            wv_month.setAdapter(new NumericWheelAdapter(startMonth, endMonth));
            wv_month.setCurrentItem(month + 1 - startMonth);
        } else if (year == startYear) {
            //起始日期的月份控制
            wv_month.setAdapter(new NumericWheelAdapter(startMonth, 12));
            wv_month.setCurrentItem(month + 1 - startMonth);
        } else if (year == endYear) {
            //终止日期的月份控制
            wv_month.setAdapter(new NumericWheelAdapter(1, endMonth));
            wv_month.setCurrentItem(month);
        } else {
            wv_month.setAdapter(new NumericWheelAdapter(1, 12));
            wv_month.setCurrentItem(month);
        }
     /*   wv_month.setLabel(context.getString(R.string.pickerview_month));*/

        wv_month.setGravity(gravity);
        // 日
        wv_day = (WheelView) view.findViewById(R.id.wl_day);

        if (startYear == endYear && startMonth == endMonth) {
            if (list_big.contains(String.valueOf(month + 1))) {
                if (endDay > 31) {
                    endDay = 31;
                }
                wv_day.setAdapter(new NumericWheelAdapter(startDay, endDay));
            } else if (list_little.contains(String.valueOf(month + 1))) {
                if (endDay > 30) {
                    endDay = 30;
                }
                wv_day.setAdapter(new NumericWheelAdapter(startDay, endDay));
            } else {
                // 闰年
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    if (endDay > 29) {
                        endDay = 29;
                    }
                    wv_day.setAdapter(new NumericWheelAdapter(startDay, endDay));
                } else {
                    if (endDay > 28) {
                        endDay = 28;
                    }
                    wv_day.setAdapter(new NumericWheelAdapter(startDay, endDay));
                }
            }
            wv_day.setCurrentItem(day - startDay);
        } else if (year == startYear && month + 1 == startMonth) {
            // 起始日期的天数控制
            if (list_big.contains(String.valueOf(month + 1))) {

                wv_day.setAdapter(new NumericWheelAdapter(startDay, 31));
            } else if (list_little.contains(String.valueOf(month + 1))) {

                wv_day.setAdapter(new NumericWheelAdapter(startDay, 30));
            } else {
                // 闰年
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {

                    wv_day.setAdapter(new NumericWheelAdapter(startDay, 29));
                } else {

                    wv_day.setAdapter(new NumericWheelAdapter(startDay, 28));
                }
            }
            wv_day.setCurrentItem(day - startDay);
        } else if (year == endYear && month + 1 == endMonth) {
            // 终止日期的天数控制
            if (list_big.contains(String.valueOf(month + 1))) {
                if (endDay > 31) {
                    endDay = 31;
                }
                wv_day.setAdapter(new NumericWheelAdapter(1, endDay));
            } else if (list_little.contains(String.valueOf(month + 1))) {
                if (endDay > 30) {
                    endDay = 30;
                }
                wv_day.setAdapter(new NumericWheelAdapter(1, endDay));
            } else {
                // 闰年
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    if (endDay > 29) {
                        endDay = 29;
                    }
                    wv_day.setAdapter(new NumericWheelAdapter(1, endDay));
                } else {
                    if (endDay > 28) {
                        endDay = 28;
                    }
                    wv_day.setAdapter(new NumericWheelAdapter(1, endDay));
                }
            }
            wv_day.setCurrentItem(day - 1);
        } else {
            // 判断大小月及是否闰年,用来确定"日"的数据
            if (list_big.contains(String.valueOf(month + 1))) {

                wv_day.setAdapter(new NumericWheelAdapter(1, 31));
            } else if (list_little.contains(String.valueOf(month + 1))) {

                wv_day.setAdapter(new NumericWheelAdapter(1, 30));
            } else {
                // 闰年
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {

                    wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                } else {

                    wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                }
            }
            wv_day.setCurrentItem(day - 1);
        }

        wv_day.setGravity(gravity);

        int len = ArrayWheelAdapter.DEFAULT_LENGTH;
        wv_option1 = (WheelView) view.findViewById(R.id.wl_options1);// 初始化时显示的数据

        int index = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 2;
        if (index < 0) {
            index += 7;
        }
        int i = 0;
        if(schedule != null && schedule.length() > 0){
            char[] arr = schedule.toCharArray();
            i = Integer.parseInt(String.valueOf(arr[index]));
        }
        List<String> list = Arrays.asList(mOptions1Items);
        switch (i) {
            case 0: // 不开放
                optionsList = new ArrayList<>();
                break;
            case 1: // 全天
                optionsList = list;
                break;
            case 2: // 上午
                optionsList = list.subList(0,1);
                break;
            case 3: // 下午
                optionsList = list.subList(1,2);
                break;
            default: // 默认全天不开放
                optionsList = new ArrayList<>();
                break;
        }
        wv_option1.setAdapter(new ArrayWheelAdapter(optionsList, len)); // 设置显示数据
        // 选项1
//        wv_option1.setAdapter(new ArrayWheelAdapter(Arrays.asList(mOptions1Items), len));// 设置显示数据
        wv_option1.setCurrentItem(0);// 初始化时显示的数据
        wv_option1.setIsOptions(true);
        wv_year.setGravity(gravity);

        // 添加"年"监听
        OnItemSelectedListener wheelListener_year = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                int year_num = index + startYear;
                currentYear = year_num;
                int currentMonthItem = wv_month.getCurrentItem();//记录上一次的item位置
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (startYear == endYear) {
                    //重新设置月份
                    wv_month.setAdapter(new NumericWheelAdapter(startMonth, endMonth));

                    if (currentMonthItem > wv_month.getAdapter().getItemsCount() - 1) {
                        currentMonthItem = wv_month.getAdapter().getItemsCount() - 1;
                        wv_month.setCurrentItem(currentMonthItem);
                    }

                    int monthNum = currentMonthItem + startMonth;

                    if (startMonth == endMonth) {
                        //重新设置日
                        setReDay(year_num, monthNum, startDay, endDay, list_big, list_little);
                    } else if (monthNum == startMonth) {
                        //重新设置日
                        setReDay(year_num, monthNum, startDay, 31, list_big, list_little);
                    } else {
                        //重新设置日
                        setReDay(year_num, monthNum, 1, 31, list_big, list_little);
                    }
                } else if (year_num == startYear) {//等于开始的年
                    //重新设置月份
                    wv_month.setAdapter(new NumericWheelAdapter(startMonth, 12));

                    if (currentMonthItem > wv_month.getAdapter().getItemsCount() - 1) {
                        currentMonthItem = wv_month.getAdapter().getItemsCount() - 1;
                        wv_month.setCurrentItem(currentMonthItem);
                    }

                    int month = currentMonthItem + startMonth;
                    if (month == startMonth) {

                        //重新设置日
                        setReDay(year_num, month, startDay, 31, list_big, list_little);
                    } else {
                        //重新设置日

                        setReDay(year_num, month, 1, 31, list_big, list_little);
                    }

                } else if (year_num == endYear) {
                    //重新设置月份
                    wv_month.setAdapter(new NumericWheelAdapter(1, endMonth));
                    if (currentMonthItem > wv_month.getAdapter().getItemsCount() - 1) {
                        currentMonthItem = wv_month.getAdapter().getItemsCount() - 1;
                        wv_month.setCurrentItem(currentMonthItem);
                    }
                    int monthNum = currentMonthItem + 1;

                    if (monthNum == endMonth) {
                        //重新设置日
                        setReDay(year_num, monthNum, 1, endDay, list_big, list_little);
                    } else {
                        //重新设置日
                        setReDay(year_num, monthNum, 1, 31, list_big, list_little);
                    }

                } else {
                    //重新设置月份
                    wv_month.setAdapter(new NumericWheelAdapter(1, 12));
                    //重新设置日
                    setReDay(year_num, wv_month.getCurrentItem() + 1, 1, 31, list_big, list_little);

                }
            }
        };
        // 添加"月"监听
        OnItemSelectedListener wheelListener_month = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                int month_num = index + 1;

                if (startYear == endYear) {
                    month_num = month_num + startMonth - 1;
                    if (startMonth == endMonth) {
                        //重新设置日
                        setReDay(currentYear, month_num, startDay, endDay, list_big, list_little);
                    } else if (startMonth == month_num) {

                        //重新设置日
                        setReDay(currentYear, month_num, startDay, 31, list_big, list_little);
                    } else if (endMonth == month_num) {
                        setReDay(currentYear, month_num, 1, endDay, list_big, list_little);
                    } else {
                        setReDay(currentYear, month_num, 1, 31, list_big, list_little);
                    }
                } else if (currentYear == startYear) {
                    month_num = month_num + startMonth - 1;
                    if (month_num == startMonth) {
                        //重新设置日
                        setReDay(currentYear, month_num, startDay, 31, list_big, list_little);
                    } else {
                        //重新设置日
                        setReDay(currentYear, month_num, 1, 31, list_big, list_little);
                    }

                } else if (currentYear == endYear) {
                    if (month_num == endMonth) {
                        //重新设置日
                        setReDay(currentYear, wv_month.getCurrentItem() + 1, 1, endDay, list_big, list_little);
                    } else {
                        setReDay(currentYear, wv_month.getCurrentItem() + 1, 1, 31, list_big, list_little);
                    }

                } else {
                    //重新设置日
                    setReDay(currentYear, month_num, 1, 31, list_big, list_little);
                }


            }
        };
        wv_year.setOnItemSelectedListener(wheelListener_year);
        wv_month.setOnItemSelectedListener(wheelListener_month);
        wv_day.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                Log.e("=====",index+"");
            }
        });
        if (type.length != 3) {
            throw new IllegalArgumentException("type[] length is not 6");
        }
        wv_year.setVisibility(type[0] ? View.VISIBLE : View.GONE);
        wv_month.setVisibility(type[1] ? View.VISIBLE : View.GONE);
        wv_day.setVisibility(type[2] ? View.VISIBLE : View.GONE);
        setContentTextSize();
    }

//        wv_year.setGravity(gravity);
//        // 月
//        wv_month = (WheelView) view.findViewById(R.id.wl_month);
//        if (startYear == endYear) {//开始年等于终止年
//            wv_month.setAdapter(new NumericWheelAdapter(startMonth, endMonth));
//            wv_month.setCurrentItem(month + 1 - startMonth);
//        } else if (year == startYear) {
//            //起始日期的月份控制
//            wv_month.setAdapter(new NumericWheelAdapter(startMonth, 12));
//            wv_month.setCurrentItem(month + 1 - startMonth);
//        } else if (year == endYear) {
//            //终止日期的月份控制
//            wv_month.setAdapter(new NumericWheelAdapter(1, endMonth));
//            wv_month.setCurrentItem(month);
//        } else {
//            wv_month.setAdapter(new NumericWheelAdapter(1, 12));
//            wv_month.setCurrentItem(month);
//        }
//     /*   wv_month.setLabel(context.getString(R.string.pickerview_month));*/
//
//        wv_month.setGravity(gravity);
//        // 日
//        wv_day = (WheelView) view.findViewById(R.id.wl_day);
//
//        if (startYear == endYear && startMonth == endMonth) {
//            if (list_big.contains(String.valueOf(month + 1))) {
//                if (endDay > 31) {
//                    endDay = 31;
//                }
//                wv_day.setAdapter(new NumericWheelAdapter(startDay, endDay));
//            } else if (list_little.contains(String.valueOf(month + 1))) {
//                if (endDay > 30) {
//                    endDay = 30;
//                }
//                wv_day.setAdapter(new NumericWheelAdapter(startDay, endDay));
//            } else {
//                // 闰年
//                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
//                    if (endDay > 29) {
//                        endDay = 29;
//                    }
//                    wv_day.setAdapter(new NumericWheelAdapter(startDay, endDay));
//                } else {
//                    if (endDay > 28) {
//                        endDay = 28;
//                    }
//                    wv_day.setAdapter(new NumericWheelAdapter(startDay, endDay));
//                }
//            }
//            wv_day.setCurrentItem(day - startDay);
//        } else if (year == startYear && month + 1 == startMonth) {
//            // 起始日期的天数控制
//            if (list_big.contains(String.valueOf(month + 1))) {
//
//                wv_day.setAdapter(new NumericWheelAdapter(startDay, 31));
//            } else if (list_little.contains(String.valueOf(month + 1))) {
//
//                wv_day.setAdapter(new NumericWheelAdapter(startDay, 30));
//            } else {
//                // 闰年
//                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
//
//                    wv_day.setAdapter(new NumericWheelAdapter(startDay, 29));
//                } else {
//
//                    wv_day.setAdapter(new NumericWheelAdapter(startDay, 28));
//                }
//            }
//            wv_day.setCurrentItem(day - startDay);
//        } else if (year == endYear && month + 1 == endMonth) {
//            // 终止日期的天数控制
//            if (list_big.contains(String.valueOf(month + 1))) {
//                if (endDay > 31) {
//                    endDay = 31;
//                }
//                wv_day.setAdapter(new NumericWheelAdapter(1, endDay));
//            } else if (list_little.contains(String.valueOf(month + 1))) {
//                if (endDay > 30) {
//                    endDay = 30;
//                }
//                wv_day.setAdapter(new NumericWheelAdapter(1, endDay));
//            } else {
//                // 闰年
//                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
//                    if (endDay > 29) {
//                        endDay = 29;
//                    }
//                    wv_day.setAdapter(new NumericWheelAdapter(1, endDay));
//                } else {
//                    if (endDay > 28) {
//                        endDay = 28;
//                    }
//                    wv_day.setAdapter(new NumericWheelAdapter(1, endDay));
//                }
//            }
//            wv_day.setCurrentItem(day - 1);
//        } else {
//            // 判断大小月及是否闰年,用来确定"日"的数据
//            if (list_big.contains(String.valueOf(month + 1))) {
//
//                wv_day.setAdapter(new NumericWheelAdapter(1, 31));
//            } else if (list_little.contains(String.valueOf(month + 1))) {
//
//                wv_day.setAdapter(new NumericWheelAdapter(1, 30));
//            } else {
//                // 闰年
//                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
//
//                    wv_day.setAdapter(new NumericWheelAdapter(1, 29));
//                } else {
//
//                    wv_day.setAdapter(new NumericWheelAdapter(1, 28));
//                }
//            }
//            wv_day.setCurrentItem(day - 1);
//        }
//
//        wv_day.setGravity(gravity);
//
//        int len = ArrayWheelAdapter.DEFAULT_LENGTH;
//        wv_option1 = (WheelView) view.findViewById(R.id.wl_options1);// 初始化时显示的数据
//
//        char[] arr = schedule.toCharArray();
//        int index = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 2;
//        if (index < 0) {
//            index += 7;
//        }
//        int i = Integer.parseInt(String.valueOf(arr[index]));
//        List<String> list = Arrays.asList(mOptions1Items);
//        switch (i) {
//            case 0: // 不开放
//                optionsList = new ArrayList<>();
//                break;
//            case 1: // 全天
//                optionsList = list;
//                break;
//            case 2: // 上午
//                optionsList = list.subList(0,1);
//                break;
//            case 3: // 下午
//                optionsList = list.subList(1,2);
//                break;
//            default: // 默认全天不开放
//                optionsList = new ArrayList<>();
//                break;
//        }
//        wv_option1.setAdapter(new ArrayWheelAdapter(optionsList, len)); // 设置显示数据
//        // 选项1
////        wv_option1.setAdapter(new ArrayWheelAdapter(Arrays.asList(mOptions1Items), len));// 设置显示数据
//        wv_option1.setCurrentItem(0);// 初始化时显示的数据
//        wv_option1.setIsOptions(true);
//        wv_year.setGravity(gravity);
//
//        // 添加"年"监听
//        OnItemSelectedListener wheelListener_year = new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(int index) {
//                int year_num = index + startYear;
//                currentYear = year_num;
//                int currentMonthItem = wv_month.getCurrentItem();//记录上一次的item位置
//                // 判断大小月及是否闰年,用来确定"日"的数据
//                if (startYear == endYear) {
//                    //重新设置月份
//                    wv_month.setAdapter(new NumericWheelAdapter(startMonth, endMonth));
//
//                    if (currentMonthItem > wv_month.getAdapter().getItemsCount() - 1) {
//                        currentMonthItem = wv_month.getAdapter().getItemsCount() - 1;
//                        wv_month.setCurrentItem(currentMonthItem);
//                    }
//
//                    int monthNum = currentMonthItem + startMonth;
//
//                    if (startMonth == endMonth) {
//                        //重新设置日
//                        setReDay(year_num, monthNum, startDay, endDay, list_big, list_little);
//                    } else if (monthNum == startMonth) {
//                        //重新设置日
//                        setReDay(year_num, monthNum, startDay, 31, list_big, list_little);
//                    } else {
//                        //重新设置日
//                        setReDay(year_num, monthNum, 1, 31, list_big, list_little);
//                    }
//                } else if (year_num == startYear) {//等于开始的年
//                    //重新设置月份
//                    wv_month.setAdapter(new NumericWheelAdapter(startMonth, 12));
//
//                    if (currentMonthItem > wv_month.getAdapter().getItemsCount() - 1) {
//                        currentMonthItem = wv_month.getAdapter().getItemsCount() - 1;
//                        wv_month.setCurrentItem(currentMonthItem);
//                    }
//
//                    int month = currentMonthItem + startMonth;
//                    if (month == startMonth) {
//
//                        //重新设置日
//                        setReDay(year_num, month, startDay, 31, list_big, list_little);
//                    } else {
//                        //重新设置日
//
//                        setReDay(year_num, month, 1, 31, list_big, list_little);
//                    }
//
//                } else if (year_num == endYear) {
//                    //重新设置月份
//                    wv_month.setAdapter(new NumericWheelAdapter(1, endMonth));
//                    if (currentMonthItem > wv_month.getAdapter().getItemsCount() - 1) {
//                        currentMonthItem = wv_month.getAdapter().getItemsCount() - 1;
//                        wv_month.setCurrentItem(currentMonthItem);
//                    }
//                    int monthNum = currentMonthItem + 1;
//
//                    if (monthNum == endMonth) {
//                        //重新设置日
//                        setReDay(year_num, monthNum, 1, endDay, list_big, list_little);
//                    } else {
//                        //重新设置日
//                        setReDay(year_num, monthNum, 1, 31, list_big, list_little);
//                    }
//
//                } else {
//                    //重新设置月份
//                    wv_month.setAdapter(new NumericWheelAdapter(1, 12));
//                    //重新设置日
//                    setReDay(year_num, wv_month.getCurrentItem() + 1, 1, 31, list_big, list_little);
//
//                }
//            }
//        };
//        // 添加"月"监听
//        OnItemSelectedListener wheelListener_month = new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(int index) {
//                int month_num = index + 1;
//
//                if (startYear == endYear) {
//                    month_num = month_num + startMonth - 1;
//                    if (startMonth == endMonth) {
//                        //重新设置日
//                        setReDay(currentYear, month_num, startDay, endDay, list_big, list_little);
//                    } else if (startMonth == month_num) {
//
//                        //重新设置日
//                        setReDay(currentYear, month_num, startDay, 31, list_big, list_little);
//                    } else if (endMonth == month_num) {
//                        setReDay(currentYear, month_num, 1, endDay, list_big, list_little);
//                    } else {
//                        setReDay(currentYear, month_num, 1, 31, list_big, list_little);
//                    }
//                } else if (currentYear == startYear) {
//                    month_num = month_num + startMonth - 1;
//                    if (month_num == startMonth) {
//                        //重新设置日
//                        setReDay(currentYear, month_num, startDay, 31, list_big, list_little);
//                    } else {
//                        //重新设置日
//                        setReDay(currentYear, month_num, 1, 31, list_big, list_little);
//                    }
//
//                } else if (currentYear == endYear) {
//                    if (month_num == endMonth) {
//                        //重新设置日
//                        setReDay(currentYear, wv_month.getCurrentItem() + 1, 1, endDay, list_big, list_little);
//                    } else {
//                        setReDay(currentYear, wv_month.getCurrentItem() + 1, 1, 31, list_big, list_little);
//                    }
//
//                } else {
//                    //重新设置日
//                    setReDay(currentYear, month_num, 1, 31, list_big, list_little);
//                }
//
//
//            }
//        };
//        wv_year.setOnItemSelectedListener(wheelListener_year);
//        wv_month.setOnItemSelectedListener(wheelListener_month);
//        wv_day.setOnItemSelectedListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(int index) {
//                Log.e("=====",index+"");
//            }
//        });
//        if (type.length != 3) {
//            throw new IllegalArgumentException("type[] length is not 6");
//        }
//        wv_year.setVisibility(type[0] ? View.VISIBLE : View.GONE);
//        wv_month.setVisibility(type[1] ? View.VISIBLE : View.GONE);
//        wv_day.setVisibility(type[2] ? View.VISIBLE : View.GONE);
//        setContentTextSize();
//    }


    private void setReDay(int year_num, int monthNum, int startD, int endD, List<String> list_big, List<String> list_little) {
        int currentItem = wv_day.getCurrentItem();

        int maxItem;
        if (list_big
                .contains(String.valueOf(monthNum))) {
            if (endD > 31) {
                endD = 31;
            }
            wv_day.setAdapter(new NumericWheelAdapter(startD, endD));
            maxItem = endD;
        } else if (list_little.contains(String.valueOf(monthNum))) {
            if (endD > 30) {
                endD = 30;
            }
            wv_day.setAdapter(new NumericWheelAdapter(startD, endD));
            maxItem = endD;
        } else {
            if ((year_num % 4 == 0 && year_num % 100 != 0)
                    || year_num % 400 == 0) {
                if (endD > 29) {
                    endD = 29;
                }
                wv_day.setAdapter(new NumericWheelAdapter(startD, endD));
                maxItem = endD;
            } else {
                if (endD > 28) {
                    endD = 28;
                }
                wv_day.setAdapter(new NumericWheelAdapter(startD, endD));
                maxItem = endD;
            }
        }

        if (currentItem > wv_day.getAdapter().getItemsCount() - 1) {
            currentItem = wv_day.getAdapter().getItemsCount() - 1;
            wv_day.setCurrentItem(currentItem);
        }

        Calendar sel = Calendar.getInstance();
        if (currentYear == startYear) {
            if ((wv_month.getCurrentItem() + startMonth) == startMonth) {
                sel.set(wv_year.getCurrentItem() + startYear,
                        wv_month.getCurrentItem() + startMonth,
                        wv_day.getCurrentItem() + startDay - 1,0,0,0);
            } else {
                sel.set(wv_year.getCurrentItem() + startYear,
                        wv_month.getCurrentItem() + startMonth,
                        wv_day.getCurrentItem(),0,0,0);
            }
        } else {
            sel.set(wv_year.getCurrentItem() + startYear,
                    wv_month.getCurrentItem() + 1,
                    wv_day.getCurrentItem(),0,0,0);
        }
        char[] arr = schedule.toCharArray();
        int index = sel.get(Calendar.DAY_OF_WEEK) - 2;
        if (index < 0) {
            index += 7;
        }
        int i = Integer.parseInt(String.valueOf(arr[index]));
        int len = ArrayWheelAdapter.DEFAULT_LENGTH;
        List<String> list = Arrays.asList(mOptions1Items);
        switch (i) {
            case 0: // 不开放
                optionsList = new ArrayList<>();
                break;
            case 1: // 全天
                optionsList = list;
                break;
            case 2: // 上午
                optionsList = list.subList(0,1);
                break;
            case 3: // 下午
                optionsList = list.subList(1,2);
                break;
            default: // 默认全天不开放
                optionsList = new ArrayList<>();
                break;
        }
        wv_option1.setAdapter(new ArrayWheelAdapter(optionsList, len)); // 设置显示数据
    }


    private void setContentTextSize() {
        wv_day.setTextSize(textSize);
        wv_month.setTextSize(textSize);
        wv_year.setTextSize(textSize);
        wv_option1.setTextSize(textSize);
    }

    private void setTextColorOut() {
        wv_day.setTextColorOut(textColorOut);
        wv_month.setTextColorOut(textColorOut);
        wv_year.setTextColorOut(textColorOut);
        wv_option1.setTextColorOut(textColorOut);
    }

    private void setTextColorCenter() {
        wv_day.setTextColorCenter(textColorCenter);
        wv_month.setTextColorCenter(textColorCenter);
        wv_year.setTextColorCenter(textColorCenter);
        wv_option1.setTextColorCenter(textColorCenter);
    }

    private void setDividerColor() {
        wv_day.setDividerColor(dividerColor);
        wv_month.setDividerColor(dividerColor);
        wv_year.setDividerColor(dividerColor);
        wv_option1.setDividerColor(dividerColor);
    }

    private void setDividerType() {
        wv_day.setDividerType(dividerType);
        wv_month.setDividerType(dividerType);
        wv_year.setDividerType(dividerType);
        wv_option1.setDividerType(dividerType);
    }

    private void setLineSpacingMultiplier() {
        wv_day.setLineSpacingMultiplier(lineSpacingMultiplier);
        wv_month.setLineSpacingMultiplier(lineSpacingMultiplier);
        wv_year.setLineSpacingMultiplier(lineSpacingMultiplier);
        wv_option1.setLineSpacingMultiplier(lineSpacingMultiplier);
    }

    public void setLabels(String label_year, String label_month, String label_day) {
        if (label_year != null) {
            wv_year.setLabel(label_year);
        } else {
            wv_year.setLabel(view.getContext().getString(R.string.pickerview_year));
        }
        if (label_month != null) {
            wv_month.setLabel(label_month);
        } else {
            wv_month.setLabel(view.getContext().getString(R.string.pickerview_month));
        }
        if (label_day != null) {
            wv_day.setLabel(label_day);
        } else {
            wv_day.setLabel(view.getContext().getString(R.string.pickerview_day));
        }
    }


    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic) {
        wv_year.setCyclic(cyclic);
        wv_month.setCyclic(cyclic);
        wv_day.setCyclic(cyclic);
        wv_option1.setCyclic(cyclic);
    }

    public String getTime() {
        StringBuffer sb = new StringBuffer();
        if (currentYear == startYear) {
           /* int i = wv_month.getCurrentItem() + startMonth;
            System.out.println("i:" + i);*/
            if ((wv_month.getCurrentItem() + startMonth) == startMonth) {
                if(optionsList.size() > 0){
                    sb.append((wv_year.getCurrentItem() + startYear)).append("-")
                            .append((wv_month.getCurrentItem() + startMonth)).append("-")
                            .append((wv_day.getCurrentItem() + startDay)).append(" ")
                            .append(optionsList.get(wv_option1.getCurrentItem()));
                }
            } else {
                if(optionsList.size() > 0){
                    sb.append((wv_year.getCurrentItem() + startYear)).append("-")
                            .append((wv_month.getCurrentItem() + startMonth)).append("-")
                            .append((wv_day.getCurrentItem() + 1)).append(" ")
                            .append(optionsList.get(wv_option1.getCurrentItem()));
                }
            }
        } else {
            if(optionsList.size() > 0){
                sb.append((wv_year.getCurrentItem() + startYear)).append("-")
                        .append((wv_month.getCurrentItem() + 1)).append("-")
                        .append((wv_day.getCurrentItem() + 1)).append(" ")
                        .append(optionsList.get(wv_option1.getCurrentItem()));
            }
        }

        return sb.toString();
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }


    public void setRangDate(Calendar startDate, Calendar endDate) {

        if (startDate == null && endDate != null) {
            int year = endDate.get(Calendar.YEAR);
            int month = endDate.get(Calendar.MONTH) + 1;
            int day = endDate.get(Calendar.DAY_OF_MONTH);
            if (year > startYear) {
                this.endYear = year;
                this.endMonth = month;
                this.endDay = day;
            } else if (year == startYear) {
                if (month > startMonth) {
                    this.endYear = year;
                    this.endMonth = month;
                    this.endDay = day;
                } else if (month == startMonth) {
                    if (day > startDay) {
                        this.endYear = year;
                        this.endMonth = month;
                        this.endDay = day;
                    }
                }
            }

        } else if (startDate != null && endDate == null) {
            int year = startDate.get(Calendar.YEAR);
            int month = startDate.get(Calendar.MONTH) + 1;
            int day = startDate.get(Calendar.DAY_OF_MONTH);
            if (year < endYear) {
                this.startMonth = month;
                this.startDay = day;
                this.startYear = year;
            } else if (year == endYear) {
                if (month < endMonth) {
                    this.startMonth = month;
                    this.startDay = day;
                    this.startYear = year;
                } else if (month == endMonth) {
                    if (day < endDay) {
                        this.startMonth = month;
                        this.startDay = day;
                        this.startYear = year;
                    }
                }
            }

        } else if (startDate != null && endDate != null) {
            this.startYear = startDate.get(Calendar.YEAR);
            this.endYear = endDate.get(Calendar.YEAR);
            this.startMonth = startDate.get(Calendar.MONTH) + 1;
            this.endMonth = endDate.get(Calendar.MONTH) + 1;
            this.startDay = startDate.get(Calendar.DAY_OF_MONTH);
            this.endDay = endDate.get(Calendar.DAY_OF_MONTH);
        }
    }


    /**
     * 设置间距倍数,但是只能在1.0-2.0f之间
     *
     * @param lineSpacingMultiplier
     */
    public void setLineSpacingMultiplier(float lineSpacingMultiplier) {
        this.lineSpacingMultiplier = lineSpacingMultiplier;
        setLineSpacingMultiplier();
    }

    /**
     * 设置分割线的颜色
     *
     * @param dividerColor
     */
    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        setDividerColor();
    }

    /**
     * 设置分割线的类型
     *
     * @param dividerType
     */
    public void setDividerType(WheelView.DividerType dividerType) {
        this.dividerType = dividerType;
        setDividerType();
    }

    /**
     * 设置分割线之间的文字的颜色
     *
     * @param textColorCenter
     */
    public void setTextColorCenter(int textColorCenter) {
        this.textColorCenter = textColorCenter;
        setTextColorCenter();
    }

    /**
     * 设置分割线以外文字的颜色
     *
     * @param textColorOut
     */
    public void setTextColorOut(int textColorOut) {
        this.textColorOut = textColorOut;
        setTextColorOut();
    }

    /**
     * Label 是否只显示中间选中项的
     *
     * @param isCenterLabel
     */

    public void isCenterLabel(Boolean isCenterLabel) {
        wv_day.isCenterLabel(isCenterLabel);
        wv_month.isCenterLabel(isCenterLabel);
        wv_year.isCenterLabel(isCenterLabel);
        wv_option1.isCenterLabel(isCenterLabel);
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
