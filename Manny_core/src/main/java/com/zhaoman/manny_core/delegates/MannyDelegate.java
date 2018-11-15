package com.zhaoman.manny_core.delegates;


public abstract class MannyDelegate  extends PermissionCheckerDelegate{


    @SuppressWarnings("unchecked")
    public <T extends MannyDelegate> T getParentDelegate(){

        return (T) getParentFragment();
    }
}
