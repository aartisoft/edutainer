package com.edutainer.in.workplace.HomeScreen;

public class HomePresenterImpl implements HomeContract.HomePresenter{

    HomeContract.HomeView homeView;
    HomeContract.HomeInteraction homeInteraction;

    public HomePresenterImpl(HomeContract.HomeView homeView, HomeContract.HomeInteraction homeInteraction) {
        this.homeView = homeView;
        this.homeInteraction = homeInteraction;
    }

    @Override
    public void onDestroy() {

    }
}
