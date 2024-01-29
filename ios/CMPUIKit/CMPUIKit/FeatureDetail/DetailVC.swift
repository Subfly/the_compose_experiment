//
//  DetailVC.swift
//  CMPUIKit
//
//  Created by Ali Taha on 15.12.2023.
//

import Foundation
import UIKit
import composeBase

class DetailVC: UIViewController {
    private var holder: DetailScreenHolder? = nil
    
    var coinId: String = ""
    var coinSymbol: String = ""
    var coinActivity: Bool = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.setHidesBackButton(true, animated: false)
        
        let holder = DetailScreenHolder(
            coinId: coinId,
            coinSymbol: coinSymbol,
            coinActivity: coinActivity,
            onBackPressed: {
                self.navigationController?.popViewController(animated: true)
            }
        )
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
