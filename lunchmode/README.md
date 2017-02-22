    singTop和Intent.FLAG_ACTIVITY_CLEAR_TOP一起使用才起效果

    如果已经启动了四个Activity：A，B，C和D，在D Activity里，想再启动一个Actvity B，
    但不变成A,B,C,D,B，而是希望是A,C,D,B--->Intent.FLAG_ACTIVITY_REORDER_TO_FRONT