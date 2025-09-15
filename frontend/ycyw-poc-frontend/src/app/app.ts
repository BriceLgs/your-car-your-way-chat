import { Component, OnInit, signal, PLATFORM_ID, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Client } from '@stomp/stompjs';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, MatInputModule, MatButtonModule, MatListModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected readonly title = signal('Chat POC');
  private client!: Client;
  messages = signal<any[]>([]);
  newMessage = signal<string>('');
  utilisateurId = signal<number>(1);
  isConnected = signal<boolean>(false);

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
    if (isPlatformBrowser(this.platformId)) {
      this.client = new Client({
        brokerURL: 'ws://localhost:8080/ws',
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000
      });

      this.client.onConnect = () => {
        this.isConnected.set(true);
        this.client.subscribe('/topic/chat', (msg) => {
          this.messages.update(messages => [...messages, JSON.parse(msg.body)]);
        });
      };

      this.client.onStompError = (frame) => {
        this.isConnected.set(false);
      };

      this.client.onWebSocketError = (error) => {
        this.isConnected.set(false);
      };
    }
  }

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      this.client.activate();
    }
  }

  sendMessage() {
    if (this.newMessage() && isPlatformBrowser(this.platformId) && this.isConnected()) {
      this.client.publish({
        destination: '/app/chat.send',
        body: JSON.stringify({
          utilisateurId: this.utilisateurId(),
          contenu: this.newMessage()
        })
      });
      this.newMessage.set('');
    }
  }
}