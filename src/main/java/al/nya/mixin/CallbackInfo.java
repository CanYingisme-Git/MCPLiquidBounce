package al.nya.mixin;



public class CallbackInfo<V> {
    private V value;
    private boolean cancel;
    public CallbackInfo(V v){
        this.value = v;
        cancel = false;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
    public void setReturnValue(V value){
        this.value = value;
    }
    public void cancel() {
        cancel = true;
    }

    public boolean isCancel() {
        return cancel;
    }
}
