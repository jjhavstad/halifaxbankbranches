package com.halifaxbankbranches.models.halifax.data;

public class BankBranchesResult
{
    private Data[] data;

    private Meta meta;

    public Data[] getData () {
        return data;
    }

    public void setData (Data[] data) {
        this.data = data;
    }

    public Meta getMeta () {
        return meta;
    }

    public void setMeta (Meta meta) {
        this.meta = meta;
    }
}
