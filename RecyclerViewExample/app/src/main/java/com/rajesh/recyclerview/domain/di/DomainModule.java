package com.rajesh.recyclerview.domain.di;

import com.rajesh.recyclerview.domain.DataRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DomainModule {

    @Singleton
    @Provides
    public DataRepository provideDataRepo(){
        return new DataRepository();
    }
}
