package com.bignerdranch.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CrimeArgLab {
    private ArrayList<Crime> mCrimes;

    private static CrimeArgLab sCrimeLab;
    private Context mAppContext;

    private CrimeArgLab(Context appContext) {
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();
        for (int i = 0; i < 100; i++) {
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i % 2 == 0); // every other one
            mCrimes.add(c);
        }
    }

    public static CrimeArgLab get(Context c) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeArgLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public Crime getCrime(UUID id) {
        for (Crime c : mCrimes) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }
    
    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }
}

