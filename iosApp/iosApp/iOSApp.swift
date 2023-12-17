import SwiftUI
import shared

@main
struct iOSApp: App {

   init() {
        Helper().doInitKoin()
   }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
