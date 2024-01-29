//
//  HomeVC.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 8.12.2023.
//

import Foundation
import UIKit
import Combine

class HomeVC: UIViewController {
    private var cancelables: Set<AnyCancellable> = []
    
    let tableView = UITableView()
    let loadingIndicator = UIActivityIndicatorView()
    let errorView = UILabel()
    
    let vm = HomeVM(
        getAllCoinsUseCase: GetAllCoinsUseCase(
            repository: CoinPaprikaHomeRepository()
        )
    )
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        
        setupVM()
        createTableView()
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
                        self.tableView.isHidden = true
                        self.loadingIndicator.isHidden = true
                        self.loadingIndicator.stopAnimating()
                        self.errorView.isHidden = false
                    }
                    if state.isLoading {
                        self.tableView.isHidden = true
                        self.errorView.isHidden = true
                        self.loadingIndicator.isHidden = false
                        self.loadingIndicator.startAnimating()
                    }
                    if state.coins.isEmpty.not() {
                        self.tableView.reloadData()
                        self.errorView.isHidden = true
                        self.loadingIndicator.isHidden = true
                        self.loadingIndicator.stopAnimating()
                        self.tableView.isHidden = false
                    }
                }
            ).store(in: &cancelables)
        Task {
            await self.vm.getCoins()
        }
    }
    
}
