//
//  TimelineViewModel.swift
//  iosApp
//
//  Created by Admin on 01.03.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import Combine
import SwiftUI

class TimelineViewModel: ObservableObject {
    @Published var displayText: String = "Loading..."

    func startFetching() {
       // Use the adjusted Kotlin function that accepts a Swift closure
               TimelineFetcher.shared.startFetchingTimeline { timeline in
                   DispatchQueue.main.async {
                       self.displayText = timeline
                   }
               }
           }

    func stopFetching() {
        TimelineFetcher.shared.stopFetchingTimeline() // This method needs to be implemented in Kotlin
    }
}
