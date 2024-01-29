//
//  HomeViewItem.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 5.12.2023.
//

import SwiftUI

struct HomeViewItem: View {
    let model: CoinHomeModel
    
    var body: some View {
        HStack {
            HStack {
                Image(model.type.getIcon())
                    .resizable()
                    .frame(width: 48, height: 48)
                    .scaledToFill()
                VStack(alignment: .leading) {
                    Text(model.symbol)
                        .font(.title2)
                        .bold()
                    Text(model.name)
                        .font(.title3)
                        .italic()
                }
            }
            Spacer()
            Circle()
                .frame(width: 12, height: 12)
                .scaledToFill()
                .foregroundStyle(model.isActive ? .green : .gray)
        }
        .padding(.horizontal)
    }
}

#Preview {
    HomeViewItem(
        model: CoinHomeModel(id: "-1")
    )
}
