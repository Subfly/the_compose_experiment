//
//  HomeCoinCell.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 9.12.2023.
//

import Foundation
import UIKit

class HomeCoinCell: UITableViewCell {
    static let reuseIdentifier = "HomeCoinCell"
    
    private var coinTypeImage: UIImageView = {
        let coinTypeImage = UIImageView()
        coinTypeImage.contentMode = .scaleToFill
        coinTypeImage.tintColor = .black
        coinTypeImage.translatesAutoresizingMaskIntoConstraints = false
        return coinTypeImage
    }()
    private var coinSymbolLabel: UILabel = {
        let coinSymbolLabel = UILabel()
        coinSymbolLabel.font = .boldSystemFont(
            ofSize: UIFont.preferredFont(forTextStyle: .title2).pointSize
        )
        coinSymbolLabel.translatesAutoresizingMaskIntoConstraints = false
        return coinSymbolLabel
    }()
    private var coinNameLabel: UILabel = {
        let coinNameLabel = UILabel()
        coinNameLabel.font = .preferredFont(forTextStyle: .title3)
        coinNameLabel.translatesAutoresizingMaskIntoConstraints = false
        return coinNameLabel
    }()
    private var coinActivityIndicator: UIView = {
        let coinActivityIndicator = UIView()
        coinActivityIndicator.layer.cornerRadius = 6
        coinActivityIndicator.translatesAutoresizingMaskIntoConstraints = false
        return coinActivityIndicator
    }()
    
    override init(
        style: UITableViewCell.CellStyle,
        reuseIdentifier: String?
    ) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupUI()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func configure(with model: CoinHomeModel) {
        self.coinTypeImage.image = UIImage(resource: model.type.getIcon())
        self.coinSymbolLabel.text = model.symbol
        self.coinNameLabel.text = model.name
        self.coinActivityIndicator.backgroundColor = model.isActive ? .green : .gray
    }
    
    private func setupUI() {
        self.contentView.addSubview(coinTypeImage)
        self.contentView.addSubview(coinActivityIndicator)
        
        let stackView = UIStackView()
        stackView.axis = .vertical
        stackView.addArrangedSubview(coinSymbolLabel)
        stackView.addArrangedSubview(coinNameLabel)
        
        stackView.translatesAutoresizingMaskIntoConstraints = false
        self.contentView.addSubview(stackView)
        
        
        NSLayoutConstraint.activate([
            coinTypeImage.topAnchor.constraint(
                equalTo: self.contentView.layoutMarginsGuide.topAnchor
            ),
            coinTypeImage.bottomAnchor.constraint(
                equalTo: self.contentView.layoutMarginsGuide.bottomAnchor
            ),
            coinTypeImage.leadingAnchor.constraint(
                equalTo: self.contentView.layoutMarginsGuide.leadingAnchor
            ),
            coinTypeImage.widthAnchor.constraint(equalToConstant: 48)
        ])
        
        
        NSLayoutConstraint.activate([
            stackView.topAnchor.constraint(
                equalTo: self.contentView.topAnchor,
                constant: 8
            ),
            stackView.bottomAnchor.constraint(
                equalTo: self.contentView.bottomAnchor,
                constant: -8
            ),
            stackView.leadingAnchor.constraint(
                equalTo: coinTypeImage.trailingAnchor,
                constant: 8
            ),
            stackView.trailingAnchor.constraint(
                equalTo: self.contentView.trailingAnchor,
                constant: -8
            ),
        ])
        
        NSLayoutConstraint.activate([
            coinActivityIndicator.centerYAnchor.constraint(
                equalTo: self.contentView.centerYAnchor
            ),
            coinActivityIndicator.trailingAnchor.constraint(
                equalTo: self.contentView.trailingAnchor,
                constant: -12
            ),
            coinActivityIndicator.widthAnchor.constraint(equalToConstant: 12),
            coinActivityIndicator.heightAnchor.constraint(equalToConstant: 12)
        ])
    }
}
