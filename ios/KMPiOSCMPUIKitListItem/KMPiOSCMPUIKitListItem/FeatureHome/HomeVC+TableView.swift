//
//  HomeVC+TableView.swift
//  KMPiOSCMPUIKitListItem
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import UIKit

extension HomeVC : UITableViewDataSource, UITableViewDelegate {
    func createTableView() {
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.showsVerticalScrollIndicator = false
        
        self.tableView.register(
            HomeCoinCell.self,
            forCellReuseIdentifier: HomeCoinCell.reuseIdentifier
        )
        
        self.tableView.translatesAutoresizingMaskIntoConstraints = false
        self.view.addSubview(self.tableView)
        
        NSLayoutConstraint.activate([
            self.tableView.topAnchor.constraint(equalTo: self.view.topAnchor),
            self.tableView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            self.tableView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            self.tableView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor)
        ])
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        self.vm.state.value.coins.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let currentCoin = self.vm.state.value.coins[indexPath.row]
        guard let cell = tableView.dequeueReusableCell(
            withIdentifier: HomeCoinCell.reuseIdentifier,
            for: indexPath
        ) as? HomeCoinCell else {
            fatalError("Cannot dequeue cell with identifier: \(currentCoin.id)")
        }
        let controller = cell.configure(with: currentCoin)
        let storedController = HomeCoinCellControllerRepository.shared.getController(
            for: currentCoin.id
        )
        if storedController == nil {
            HomeCoinCellControllerRepository.shared.addController(
                for: currentCoin.id,
                controller: controller
            )
            self.addChild(controller)
        }
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let currentCoin = self.vm.state.value.coins[indexPath.row]
        
        let detailVC = DetailVC()
        detailVC.coinId = currentCoin.id
        detailVC.coinSymbol = currentCoin.symbol
        detailVC.coinActivity = currentCoin.isActive
        
        self.tableView.deselectRow(at: indexPath, animated: true)
        navigationController?.pushViewController(detailVC, animated: true)
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80
    }
}
