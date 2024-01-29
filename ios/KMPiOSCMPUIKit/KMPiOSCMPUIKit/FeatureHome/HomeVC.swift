//
//  HomeVC.swift
//  KMPiOSCMPUIKit
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import UIKit
import Combine
import composeBase

class HomeVC: UIViewController {
    private var cancelables: Set<AnyCancellable> = []
    
    let loadingIndicator = UIActivityIndicatorView()
    let errorView = UILabel()
    
    let vm = HomeVM()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        setupVM()
        createLoadingIndicator()
        createErrorView()
        createNavigationBar()
    }
    
    private func createNavigationBar() {
        self.title = "Home"
        self.navigationController?.navigationBar.prefersLargeTitles = true
        self.navigationItem.largeTitleDisplayMode = .always
    }
    
    private func createLoadingIndicator() {
        self.loadingIndicator.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(self.loadingIndicator)
        
        NSLayoutConstraint.activate([
            self.loadingIndicator.centerXAnchor.constraint(
                equalTo: self.view.centerXAnchor
            ),
            self.loadingIndicator.centerYAnchor.constraint(
                equalTo: self.view.centerYAnchor
            )
        ])
    }
    
    private func createComposeView(
        coins: [CoinHomeModel],
        onCliclCoin: @escaping (CoinHomeModel) -> Void
    ) -> UIViewController {
        return CreateHomeContentViewControllerKt.createHomeContentViewController(
            coins: coins,
            onClickItem: onCliclCoin
        )
    }
    
    private func createErrorView() {
        self.errorView.text = ""
        self.errorView.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(self.errorView)
        
        NSLayoutConstraint.activate([
            self.errorView.centerXAnchor.constraint(
                equalTo: self.view.centerXAnchor
            ),
            self.errorView.centerYAnchor.constraint(
                equalTo: self.view.centerYAnchor
            )
        ])
    }
    
    private func setupVM() {
        self.vm.state
            .receive(on: DispatchQueue.main)
            .sink(
                receiveCompletion: {
                    print($0)
                },
                receiveValue: { state in
                    if state.error.isEmpty.not() {
                        self.errorView.text = state.error
                        self.loadingIndicator.isHidden = true
                        self.loadingIndicator.stopAnimating()
                        self.errorView.isHidden = false
                    }
                    if state.isLoading {
                        self.errorView.isHidden = true
                        self.loadingIndicator.isHidden = false
                        self.loadingIndicator.startAnimating()
                    }
                    if state.coins.isEmpty.not() {
                        self.errorView.isHidden = true
                        self.loadingIndicator.isHidden = true
                        self.loadingIndicator.stopAnimating()
                        let composeListView = self.createComposeView(
                            coins: state.coins,
                            onCliclCoin: { coin in
                                let detailVC = DetailVC()
                                detailVC.coinId = coin.id
                                detailVC.coinSymbol = coin.symbol
                                detailVC.coinActivity = coin.isActive
                                self.navigationController?.pushViewController(
                                    detailVC,
                                    animated: true
                                )
                            }
                        )
                        composeListView.view.translatesAutoresizingMaskIntoConstraints = false
                        
                        self.addChild(composeListView)
                        self.view.addSubview(composeListView.view)
                        NSLayoutConstraint.activate([
                            composeListView.view.widthAnchor.constraint(
                                equalTo: self.view.widthAnchor
                            ),
                            composeListView.view.heightAnchor.constraint(
                                equalTo: self.view.heightAnchor
                            ),
                            composeListView.view.topAnchor.constraint(
                                equalTo: self.navigationController?.navigationBar.bottomAnchor ?? self.view.topAnchor
                            ),
                            composeListView.view.bottomAnchor.constraint(
                                equalTo: self.view.bottomAnchor
                            )
                        ])
                        composeListView.didMove(toParent: self)
                    }
                }
            ).store(in: &cancelables)
        Task {
            self.vm.startObserving()
        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        self.vm.dispose()
        super.viewWillDisappear(animated)
    }
    
}
