//
//  HomeCoinCell.swift
//  KMPiOSCMPUIKitListItem
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import UIKit
import composeBase

class HomeCoinCell: UITableViewCell {
    static let reuseIdentifier = "HomeCoinCell"
    
    override init(
        style: UITableViewCell.CellStyle,
        reuseIdentifier: String?
    ) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func configure(with model: CoinHomeModel) -> UIViewController {
        let coinTileController = CreateHomeItemViewControllerKt.createHomeItemViewController(
            model: model
        ) { _ in}
        coinTileController.view.translatesAutoresizingMaskIntoConstraints = false
        self.contentView.addSubview(coinTileController.view)
        
        NSLayoutConstraint.activate([
            coinTileController.view.topAnchor.constraint(
                equalTo: self.contentView.layoutMarginsGuide.topAnchor
            ),
            coinTileController.view.bottomAnchor.constraint(
                equalTo: self.contentView.layoutMarginsGuide.bottomAnchor
            ),
            coinTileController.view.leadingAnchor.constraint(
                equalTo: self.contentView.layoutMarginsGuide.leadingAnchor
            ),
            coinTileController.view.trailingAnchor.constraint(
                equalTo: self.contentView.layoutMarginsGuide.trailingAnchor
            )
        ])
        return coinTileController
    }
}
