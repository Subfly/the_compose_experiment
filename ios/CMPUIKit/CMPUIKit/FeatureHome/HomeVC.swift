//
//  HomeVC.swift
//  CMPUIKit
//
//  Created by Ali Taha on 15.12.2023.
//

import Foundation
import UIKit
import composeBase

class HomeVC: UIViewController {
    private var holder: HomeScreenHolder? = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let holder = HomeScreenHolder { model in
            let detailVC = DetailVC()
            detailVC.coinId = model.id
            detailVC.coinSymbol = model.symbol
            detailVC.coinActivity = model.isActive
            
            self.navigationController?.pushViewController(
                detailVC,
                animated: true
            )
        }
        holder.viewController.view.translatesAutoresizingMaskIntoConstraints = false
        
        self.addChild(holder.viewController)
        self.view.addSubview(holder.viewController.view)
        
        NSLayoutConstraint.activate([
            holder.viewController.view.topAnchor.constraint(
                equalTo: self.view.layoutMarginsGuide.topAnchor
            ),
            holder.viewController.view.bottomAnchor.constraint(
                equalTo: self.view.bottomAnchor
            ),
            holder.viewController.view.leadingAnchor.constraint(
                equalTo: self.view.leadingAnchor
            ),
            holder.viewController.view.trailingAnchor.constraint(
                equalTo: self.view.trailingAnchor
            )
        ])
        
        self.holder = holder
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        self.holder?.release()
        super.viewDidDisappear(animated)
    }
}
