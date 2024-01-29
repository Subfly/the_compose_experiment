//
//  DetailVC.swift
//  KMPiOSUIKit
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import UIKit
import Combine

class DetailVC: UIViewController {
    private var cancelables: Set<AnyCancellable> = []
    private let vm = DetailVM()
    
    private let scrollView: UIScrollView = {
        let scrollView = UIScrollView()
        scrollView.showsHorizontalScrollIndicator = false
        scrollView.showsVerticalScrollIndicator = false
        scrollView.translatesAutoresizingMaskIntoConstraints = false
        return scrollView
    }()

    private let coinImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleToFill
        imageView.tintColor = .black
        imageView.translatesAutoresizingMaskIntoConstraints = false
        return imageView
    }()

    private let nameLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.boldSystemFont(
            ofSize: UIFont.preferredFont(forTextStyle: .largeTitle).pointSize
        )
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()

    private let messageLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.preferredFont(forTextStyle: .body)
        label.textAlignment = .center
        label.numberOfLines = 0
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()

    private let descriptionLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.preferredFont(forTextStyle: .body)
        label.textAlignment = .justified
        label.numberOfLines = 0
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private let loadingIndicator = UIActivityIndicatorView()
    private let errorView = UILabel()
    
    var coinId: String = ""
    var coinSymbol: String = ""
    var coinActivity: Bool = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        setupVM()
        createNavigationBar()
        createContentView()
        createLoadingIndicator()
        createErrorView()
    }
    
    private func createNavigationBar() {
        let coinActivityIndicator = UIView()
        coinActivityIndicator.layer.cornerRadius = 6
        coinActivityIndicator.backgroundColor = coinActivity ? .green : .gray
        coinActivityIndicator.frame.size.height = 12
        coinActivityIndicator.frame.size.width = 12
        
        self.title = coinSymbol
        self.navigationController?.navigationBar.prefersLargeTitles = true
        self.navigationItem.largeTitleDisplayMode = .always
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(
            customView: coinActivityIndicator
        )
    }
    
    private func createContentView() {
        self.view.addSubview(self.scrollView)
        self.scrollView.addSubview(self.coinImageView)
        self.scrollView.addSubview(self.nameLabel)
        self.scrollView.addSubview(self.messageLabel)
        self.scrollView.addSubview(self.descriptionLabel)

        NSLayoutConstraint.activate([
            self.scrollView.topAnchor.constraint(equalTo: self.view.topAnchor),
            self.scrollView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            self.scrollView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            self.scrollView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor),
            
            self.coinImageView.topAnchor.constraint(equalTo: self.scrollView.topAnchor),
            self.coinImageView.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.coinImageView.widthAnchor.constraint(equalToConstant: 200),
            self.coinImageView.heightAnchor.constraint(equalToConstant: 200),

            self.nameLabel.topAnchor.constraint(equalTo: self.coinImageView.bottomAnchor, constant: 16),
            self.nameLabel.centerXAnchor.constraint(equalTo: self.scrollView.centerXAnchor),

            self.messageLabel.topAnchor.constraint(equalTo: self.nameLabel.bottomAnchor, constant: 8),
            self.messageLabel.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 16),
            self.messageLabel.trailingAnchor.constraint(
                equalTo: self.view.trailingAnchor,
                constant: -16
            ),

            self.descriptionLabel.topAnchor.constraint(
                equalTo: self.messageLabel.bottomAnchor,
                constant: 8
            ),
            self.descriptionLabel.leadingAnchor.constraint(
                equalTo: self.view.leadingAnchor,
                constant: 16
            ),
            self.descriptionLabel.trailingAnchor.constraint(
                equalTo: self.view.trailingAnchor,
                constant: -16
            )
        ])
    }
    
    private func createLoadingIndicator() {
        self.loadingIndicator.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(self.loadingIndicator)
        
        NSLayoutConstraint.activate([
            self.loadingIndicator.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.loadingIndicator.centerYAnchor.constraint(equalTo: self.view.centerYAnchor)
        ])
    }
    
    private func createErrorView() {
        self.errorView.text = ""
        self.errorView.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(self.errorView)
        
        NSLayoutConstraint.activate([
            self.errorView.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.errorView.centerYAnchor.constraint(equalTo: self.view.centerYAnchor)
        ])
    }
    
    private func setupVM() {
        vm.state
            .receive(on: DispatchQueue.main)
            .sink(
                receiveCompletion: {
                    print($0)
                },
                receiveValue: { state in
                    if state.error.isEmpty.not() {
                        self.errorView.text = state.error
                        self.scrollView.isHidden = true
                        self.loadingIndicator.isHidden = true
                        self.loadingIndicator.stopAnimating()
                        self.errorView.isHidden = false
                    }
                    if state.isLoading {
                        self.scrollView.isHidden = true
                        self.errorView.isHidden = true
                        self.loadingIndicator.isHidden = false
                        self.loadingIndicator.startAnimating()
                    }
                    if let coin = state.coin {
                        self.coinImageView.image = UIImage(
                            resource: coin.type.getIcon()
                        )
                        self.nameLabel.text = coin.name
                        self.messageLabel.text = coin.message
                        self.descriptionLabel.text = coin.coinDescription
                        self.errorView.isHidden = true
                        self.loadingIndicator.isHidden = true
                        self.loadingIndicator.stopAnimating()
                        self.scrollView.isHidden = false
                    }
                }
            ).store(in: &cancelables)
        Task {
            self.vm.startObserving()
            self.vm.fetchCoin(with: coinId)
        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        self.vm.dispose()
        super.viewWillDisappear(animated)
    }
    
}
