//
//  DetailImageView.swift
//  KMPiOSCMPSwiftUIListItem
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import SwiftUI
import composeBase

struct DetailImageView : UIViewControllerRepresentable {
    let type: CoinType
    
    func makeUIViewController(context: Context) -> UIViewController {
        return CreateCoinImageViewControllerKt.createCoinImageViewController(coinType: type)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
