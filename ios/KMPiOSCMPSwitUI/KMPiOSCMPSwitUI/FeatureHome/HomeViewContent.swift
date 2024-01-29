//
//  HomeViewContent.swift
//  KMPiOSCMPSwitUI
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import SwiftUI
import composeBase

struct HomeViewContent : UIViewControllerRepresentable {
    let coinList: [CoinHomeModel]
    let onClickItem: (CoinHomeModel) -> Void
    
    func makeUIViewController(context: Context) -> UIViewController {
        return CreateHomeContentViewControllerKt.createHomeContentViewController(
            coins: coinList,
            onClickItem: onClickItem
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
