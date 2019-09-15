package com.rajesh.recyclerview.domain.di;

import com.rajesh.recyclerview.domain.DataRepository;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DomainModule.class})
public interface DomainComponent {

    DataRepository getDataRepo();

}
